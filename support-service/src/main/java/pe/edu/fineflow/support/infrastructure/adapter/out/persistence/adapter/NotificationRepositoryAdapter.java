package pe.edu.fineflow.support.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.fineflow.support.domain.model.Notification;
import pe.edu.fineflow.support.domain.port.out.NotificationRepositoryPort;
import pe.edu.fineflow.support.infrastructure.adapter.out.persistence.entity.NotificationEntity;
import pe.edu.fineflow.support.infrastructure.adapter.out.persistence.repository.NotificationR2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class NotificationRepositoryAdapter implements NotificationRepositoryPort {

    private final NotificationR2dbcRepository repository;

    @Override
    public Mono<Notification> save(Notification n) {
        return repository.save(toEntity(n)).map(this::toModel);
    }

    @Override
    public Flux<Notification> findUnreadByUserIdAndSchoolId(String userId, String schoolId) {
        return repository.findByUserIdAndSchoolIdAndIsReadFalse(userId, schoolId).map(this::toModel);
    }

    @Override
    public Flux<Notification> findByRoleAndSchoolId(String role, String schoolId) {
        return repository.findByTargetRoleAndSchoolId(role, schoolId).map(this::toModel);
    }

    @Override
    public Mono<Long> countUnreadByUserId(String userId, String schoolId) {
        return repository.findByUserIdAndSchoolIdAndIsReadFalse(userId, schoolId).count();
    }

    @Override
    public Mono<Notification> markAsRead(String id, String schoolId) {
        return repository.findById(id)
            .filter(e -> e.getSchoolId().equals(schoolId))
            .flatMap(e -> {
                e.setRead(true);
                e.setReadAt(Instant.now());
                return repository.save(e);
            })
            .map(this::toModel);
    }

    @Override
    public Mono<Void> markAllAsReadByUserId(String userId, String schoolId) {
        return repository.findByUserIdAndSchoolIdAndIsReadFalse(userId, schoolId)
            .flatMap(e -> {
                e.setRead(true);
                e.setReadAt(Instant.now());
                return repository.save(e);
            })
            .then();
    }

    private NotificationEntity toEntity(Notification m) {
        NotificationEntity e = new NotificationEntity();
        e.setId(m.getId());
        e.setSchoolId(m.getSchoolId());
        e.setUserId(m.getUserId());
        e.setTargetRole(m.getTargetRole());
        e.setNotificationType(m.getNotificationType());
        e.setTitle(m.getTitle());
        e.setBody(m.getBody());
        e.setActionUrl(m.getActionUrl());
        e.setMetadataJson(m.getMetadataJson());
        e.setRead(m.isRead());
        e.setReadAt(m.getReadAt());
        e.setExpiresAt(m.getExpiresAt());
        return e;
    }

    private Notification toModel(NotificationEntity e) {
        return new Notification(
            e.getId(), e.getSchoolId(), e.getUserId(), e.getTargetRole(),
            e.getNotificationType(), e.getTitle(), e.getBody(), e.getActionUrl(),
            e.getMetadataJson(), e.isRead(), e.getReadAt(), e.getExpiresAt(), e.getCreatedAt()
        );
    }
}
