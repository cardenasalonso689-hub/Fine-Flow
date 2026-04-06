package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.entity.ClassTaskEntity;
import reactor.core.publisher.Flux;

@Repository
public interface ClassTaskR2dbcRepository extends ReactiveCrudRepository<ClassTaskEntity, String> {
    Flux<ClassTaskEntity> findAllBySchoolId(String schoolId);
    Flux<ClassTaskEntity> findAllByCourseAssignmentId(String courseAssignmentId);
}
