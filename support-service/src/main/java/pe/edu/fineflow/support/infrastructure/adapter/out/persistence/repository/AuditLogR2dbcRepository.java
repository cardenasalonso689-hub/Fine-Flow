package pe.edu.fineflow.support.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.support.infrastructure.adapter.out.persistence.entity.AuditLogEntity;
import reactor.core.publisher.Flux;

@Repository
public interface AuditLogR2dbcRepository extends R2dbcRepository<AuditLogEntity, String> {
    Flux<AuditLogEntity> findBySchoolIdAndAction(String schoolId, String action);
    Flux<AuditLogEntity> findBySchoolIdAndUserId(String schoolId, String userId);
}
