package pe.edu.fineflow.support.application.service;
import org.springframework.stereotype.Service;
import pe.edu.fineflow.common.util.UuidGenerator;
import pe.edu.fineflow.support.domain.model.AuditLog;
import pe.edu.fineflow.support.domain.port.out.AuditLogRepositoryPort;
import reactor.core.publisher.Mono;
import java.time.Instant;

@Service
public class AuditLogService {
    private final AuditLogRepositoryPort repo;
    public AuditLogService(AuditLogRepositoryPort repo) { this.repo = repo; }

    public Mono<AuditLog> log(String schoolId, String userId, String action,
                               String entityType, String entityId,
                               String oldJson, String newJson, String result) {
        AuditLog log = new AuditLog();
        log.setId(UuidGenerator.generate());
        log.setSchoolId(schoolId); log.setUserId(userId);
        log.setAction(action); log.setEntityType(entityType); log.setEntityId(entityId);
        log.setOldValueJson(oldJson); log.setNewValueJson(newJson);
        log.setResult(result); log.setCreatedAt(Instant.now());
        return repo.save(log);
    }
}
