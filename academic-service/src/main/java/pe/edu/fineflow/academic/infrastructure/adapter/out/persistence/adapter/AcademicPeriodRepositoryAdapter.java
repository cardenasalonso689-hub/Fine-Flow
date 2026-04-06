package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.fineflow.academic.domain.model.AcademicPeriod;
import pe.edu.fineflow.academic.domain.port.out.AcademicPeriodRepositoryPort;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.mapper.AcademicPeriodMapper;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.repository.AcademicPeriodR2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AcademicPeriodRepositoryAdapter implements AcademicPeriodRepositoryPort {
    private final AcademicPeriodR2dbcRepository repository;
    private final AcademicPeriodMapper mapper;

    @Override
    public Mono<AcademicPeriod> save(AcademicPeriod period) {
        return repository.save(mapper.toEntity(period)).map(mapper::toDomain);
    }

    @Override
    public Mono<AcademicPeriod> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Flux<AcademicPeriod> findAllBySchoolId(String schoolId) {
        return repository.findAllBySchoolId(schoolId).map(mapper::toDomain);
    }

    @Override
    public Flux<AcademicPeriod> findAllBySchoolYearId(String schoolYearId) {
        return repository.findAllBySchoolYearId(schoolYearId).map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
