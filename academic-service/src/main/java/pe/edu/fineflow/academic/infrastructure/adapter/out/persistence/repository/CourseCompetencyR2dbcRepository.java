package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.entity.CourseCompetencyEntity;
import reactor.core.publisher.Flux;

@Repository
public interface CourseCompetencyR2dbcRepository extends ReactiveCrudRepository<CourseCompetencyEntity, String> {
    Flux<CourseCompetencyEntity> findAllBySchoolId(String schoolId);
    Flux<CourseCompetencyEntity> findAllByCourseId(String courseId);
}
