package pe.edu.fineflow.support.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.fineflow.support.domain.model.AuditLog;
import pe.edu.fineflow.support.domain.port.out.AuditLogRepositoryPort;
import pe.edu.fineflow.support.infrastructure.adapter.out.persistence.entity.AuditLogEntity;
import pe.edu.fineflow.support.infrastructure.adapter.out.persistence.repository.AuditLogR2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuditLogRepositoryAdapter implements AuditLogRepositoryPort {

    private final AuditLogR2dbcRepository repository;

    @Override
    public Mono<AuditLog> save(AuditLog log) {
        return repository.save(toEntity(log)).map(this::toModel);
    }

    @Override
    public Flux<AuditLog> findBySchoolIdAndAction(String schoolId, String action) {
        return repository.findBySchoolIdAndAction(schoolId, action).map(this::toModel);
    }

    @Override
    public Flux<AuditLog> findBySchoolIdAndUserId(String schoolId, String userId) {
        return repository.findBySchoolIdAndUserId(schoolId, userId).map(this::toModel);
    }

    private AuditLogEntity toEntity(AuditLog m) {
        AuditLogEntity e = new AuditLogEntity();
        e.setId(m.getId());
        e.setSchoolId(m.getSchoolId());
        e.setUserId(m.getUserId());
        e.setAction(m.getAction());
        e.setEntityType(m.getEntityType());
        e.setEntityId(m.getEntityId());
        e.setOldValueJson(m.getOldValueJson());
        e.setNewValueJson(m.getNewValueJson());
        e.setIpAddress(m.getIpAddress());
        e.setUserAgent(m.getUserAgent());
        e.setResult(m.getResult());
        e.setErrorDetail(m.getErrorDetail());
        e.setDurationMs(m.getDurationMs());
        return e;
    }

    private AuditLog toModel(AuditLogEntity e) {
        return new AuditLog(
            e.getId(), e.getSchoolId(), e.getUserId(), e.getAction(),
            e.getEntityType(), e.getEntityId(), e.getOldValueJson(), e.getNewValueJson(),
            e.getIpAddress(), e.getUserAgent(), e.getResult(), e.getErrorDetail(),
            e.getDurationMs(), e.getCreatedAt()
        );
    }
}
