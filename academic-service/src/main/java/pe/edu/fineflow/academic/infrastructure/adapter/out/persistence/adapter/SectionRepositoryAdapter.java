package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.fineflow.academic.domain.model.Section;
import pe.edu.fineflow.academic.domain.port.out.SectionRepositoryPort;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.mapper.SectionMapper;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.repository.SectionR2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SectionRepositoryAdapter implements SectionRepositoryPort {
    private final SectionR2dbcRepository repository;
    private final SectionMapper mapper;

    @Override
    public Mono<Section> save(Section section) {
        return repository.save(mapper.toEntity(section)).map(mapper::toDomain);
    }

    @Override
    public Mono<Section> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Flux<Section> findAllBySchoolId(String schoolId) {
        return repository.findAllBySchoolId(schoolId).map(mapper::toDomain);
    }

    @Override
    public Flux<Section> findAllBySchoolYearId(String schoolYearId) {
        return repository.findAllBySchoolYearId(schoolYearId).map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
