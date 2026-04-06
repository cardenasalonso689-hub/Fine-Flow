package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.fineflow.academic.domain.model.SchoolYear;
import pe.edu.fineflow.academic.domain.port.out.SchoolYearRepositoryPort;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.mapper.SchoolYearMapper;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.repository.SchoolYearR2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SchoolYearRepositoryAdapter implements SchoolYearRepositoryPort {
    private final SchoolYearR2dbcRepository repository;
    private final SchoolYearMapper mapper;

    @Override
    public Mono<SchoolYear> save(SchoolYear year) {
        return repository.save(mapper.toEntity(year)).map(mapper::toDomain);
    }

    @Override
    public Mono<SchoolYear> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Flux<SchoolYear> findAllBySchoolId(String schoolId) {
        return repository.findAllBySchoolId(schoolId).map(mapper::toDomain);
    }

    @Override
    public Flux<SchoolYear> findAllByAcademicLevelId(String academicLevelId) {
        return repository.findAllByAcademicLevelId(academicLevelId).map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
