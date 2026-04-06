package pe.edu.fineflow.academic.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.fineflow.academic.application.port.in.ManageAcademicLevelUseCase;
import pe.edu.fineflow.academic.domain.model.AcademicLevel;
import pe.edu.fineflow.academic.domain.port.out.AcademicLevelRepositoryPort;
import pe.edu.fineflow.common.tenant.TenantContext;
import pe.edu.fineflow.common.util.UuidGenerator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ManageAcademicLevelService implements ManageAcademicLevelUseCase {
    private final AcademicLevelRepositoryPort repository;

    @Override
    public Mono<AcademicLevel> create(AcademicLevel level) {
        return TenantContext.getSchoolId().flatMap(schoolId -> {
            level.setId(UuidGenerator.generate());
            level.setSchoolId(schoolId);
            level.setIsActive(1);
            level.setCreatedAt(Instant.now());
            return repository.save(level);
        });
    }

    @Override
    public Mono<AcademicLevel> update(String id, AcademicLevel updated) {
        return TenantContext.getSchoolId().flatMap(schoolId ->
            repository.findById(id)
                .flatMap(existing -> {
                    existing.setName(updated.getName());
                    existing.setOrderNum(updated.getOrderNum());
                    existing.setIsActive(updated.getIsActive());
                    return repository.save(existing);
                })
        );
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<AcademicLevel> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<AcademicLevel> findAll() {
        return TenantContext.getSchoolId().flatMapMany(repository::findAllBySchoolId);
    }
}
