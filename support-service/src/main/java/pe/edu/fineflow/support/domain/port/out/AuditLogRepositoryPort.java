package pe.edu.fineflow.support.domain.port.out;
import pe.edu.fineflow.support.domain.model.AuditLog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface AuditLogRepositoryPort {
    Mono<AuditLog> save(AuditLog log);
    Flux<AuditLog> findBySchoolIdAndAction(String schoolId, String action);
    Flux<AuditLog> findBySchoolIdAndUserId(String schoolId, String userId);
}
