package pe.edu.fineflow.support.application.port.in;
import pe.edu.fineflow.support.domain.model.Notification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface ManageNotificationUseCase {
    Mono<Notification> create(Notification notification);
    Mono<Notification> createForRole(String schoolId, String role, String type, String title, String body);
    Flux<Notification> findMyNotifications();
    Mono<Long>         countUnread();
    Mono<Notification> markAsRead(String id);
    Mono<Void>         markAllAsRead();
}
