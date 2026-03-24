package pe.edu.fineflow.support.domain.port.out;
import pe.edu.fineflow.support.domain.model.Notification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface NotificationRepositoryPort {
    Mono<Notification> save(Notification n);
    Flux<Notification> findUnreadByUserIdAndSchoolId(String userId, String schoolId);
    Flux<Notification> findByRoleAndSchoolId(String role, String schoolId);
    Mono<Long>         countUnreadByUserId(String userId, String schoolId);
    Mono<Notification> markAsRead(String id, String schoolId);
    Mono<Void>         markAllAsReadByUserId(String userId, String schoolId);
}
