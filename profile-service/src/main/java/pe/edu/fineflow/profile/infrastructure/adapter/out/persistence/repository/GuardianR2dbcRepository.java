package pe.edu.fineflow.profile.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.profile.infrastructure.adapter.out.persistence.entity.GuardianEntity;
import reactor.core.publisher.Flux;

@Repository
public interface GuardianR2dbcRepository extends ReactiveCrudRepository<GuardianEntity, String> {
    Flux<GuardianEntity> findAllBySchoolId(String schoolId);
    Flux<GuardianEntity> findAllByStudentId(String studentId);
}
