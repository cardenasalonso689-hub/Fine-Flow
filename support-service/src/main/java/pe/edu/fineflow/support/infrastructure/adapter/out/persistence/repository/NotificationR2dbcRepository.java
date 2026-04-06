package pe.edu.fineflow.support.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.support.infrastructure.adapter.out.persistence.entity.NotificationEntity;
import reactor.core.publisher.Flux;

@Repository
public interface NotificationR2dbcRepository extends R2dbcRepository<NotificationEntity, String> {
    Flux<NotificationEntity> findByUserIdAndSchoolIdAndIsReadFalse(String userId, String schoolId);
    Flux<NotificationEntity> findByTargetRoleAndSchoolId(String role, String schoolId);
    Flux<NotificationEntity> findByUserIdAndSchoolId(String userId, String schoolId);
}
