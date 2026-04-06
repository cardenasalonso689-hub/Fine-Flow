package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.entity.AcademicPeriodEntity;
import reactor.core.publisher.Flux;

@Repository
public interface AcademicPeriodR2dbcRepository extends ReactiveCrudRepository<AcademicPeriodEntity, String> {
    Flux<AcademicPeriodEntity> findAllBySchoolId(String schoolId);
    Flux<AcademicPeriodEntity> findAllBySchoolYearId(String schoolYearId);
}
