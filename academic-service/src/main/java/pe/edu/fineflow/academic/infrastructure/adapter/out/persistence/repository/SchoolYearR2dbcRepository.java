package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.entity.SchoolYearEntity;
import reactor.core.publisher.Flux;

@Repository
public interface SchoolYearR2dbcRepository extends ReactiveCrudRepository<SchoolYearEntity, String> {
    Flux<SchoolYearEntity> findAllBySchoolId(String schoolId);
    Flux<SchoolYearEntity> findAllByAcademicLevelId(String academicLevelId);
}
