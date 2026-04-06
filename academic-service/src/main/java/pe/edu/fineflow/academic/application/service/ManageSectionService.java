package pe.edu.fineflow.academic.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.fineflow.academic.application.port.in.ManageSectionUseCase;
import pe.edu.fineflow.academic.domain.model.Section;
import pe.edu.fineflow.academic.domain.port.out.SectionRepositoryPort;
import pe.edu.fineflow.common.tenant.TenantContext;
import pe.edu.fineflow.common.util.UuidGenerator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ManageSectionService implements ManageSectionUseCase {
    private final SectionRepositoryPort repository;

    @Override
    public Mono<Section> create(Section section) {
        return TenantContext.getSchoolId().flatMap(schoolId -> {
            section.setId(UuidGenerator.generate());
            section.setSchoolId(schoolId);
            section.setIsActive(1);
            section.setCreatedAt(Instant.now());
            return repository.save(section);
        });
    }

    @Override
    public Mono<Section> update(String id, Section updated) {
        return repository.findById(id)
            .flatMap(existing -> {
                existing.setName(updated.getName());
                existing.setMaxCapacity(updated.getMaxCapacity());
                existing.setTutorId(updated.getTutorId());
                existing.setIsActive(updated.getIsActive());
                return repository.save(existing);
            });
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Section> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Section> findAll() {
        return TenantContext.getSchoolId().flatMapMany(repository::findAllBySchoolId);
    }

    @Override
    public Flux<Section> findBySchoolYear(String schoolYearId) {
        return repository.findAllBySchoolYearId(schoolYearId);
    }
}
