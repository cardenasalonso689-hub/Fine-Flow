package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.entity.CourseAssignmentEntity;
import reactor.core.publisher.Flux;

@Repository
public interface CourseAssignmentR2dbcRepository extends ReactiveCrudRepository<CourseAssignmentEntity, String> {
    Flux<CourseAssignmentEntity> findAllBySchoolId(String schoolId);
    Flux<CourseAssignmentEntity> findAllBySectionId(String sectionId);
}
