package pe.edu.fineflow.support.infrastructure.adapter.in.web;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.fineflow.support.application.port.in.ManageNotificationUseCase;
import pe.edu.fineflow.support.domain.model.Notification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/support/notifications")
@Tag(name = "Notificaciones", description = "Centro de notificaciones in-app")
public class NotificationController {

    private final ManageNotificationUseCase useCase;
    public NotificationController(ManageNotificationUseCase useCase) { this.useCase = useCase; }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public Flux<Notification> findMy() { return useCase.findMyNotifications(); }

    @GetMapping("/count")
    @PreAuthorize("isAuthenticated()")
    public Mono<Long> countUnread() { return useCase.countUnread(); }

    @PatchMapping("/{id}/read")
    @PreAuthorize("isAuthenticated()")
    public Mono<Notification> markRead(@PathVariable String id) { return useCase.markAsRead(id); }

    @PatchMapping("/read-all")
    @PreAuthorize("isAuthenticated()")
    public Mono<Void> markAllRead() { return useCase.markAllAsRead(); }
}
