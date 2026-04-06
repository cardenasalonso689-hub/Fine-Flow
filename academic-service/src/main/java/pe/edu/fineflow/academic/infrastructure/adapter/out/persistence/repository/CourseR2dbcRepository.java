package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.entity.CourseEntity;
import reactor.core.publisher.Flux;

@Repository
public interface CourseR2dbcRepository extends ReactiveCrudRepository<CourseEntity, String> {
    Flux<CourseEntity> findAllBySchoolId(String schoolId);
}
