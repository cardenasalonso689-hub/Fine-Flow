package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.entity.AcademicLevelEntity;
import reactor.core.publisher.Flux;

@Repository
public interface AcademicLevelR2dbcRepository extends ReactiveCrudRepository<AcademicLevelEntity, String> {
    Flux<AcademicLevelEntity> findAllBySchoolId(String schoolId);
}
