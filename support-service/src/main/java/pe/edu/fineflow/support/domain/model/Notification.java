package pe.edu.fineflow.support.domain.model;

import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private String id;
    private String schoolId;
    private String userId;
    private String targetRole;
    private String notificationType;
    private String title;
    private String body;
    private String actionUrl;
    private String metadataJson;
    private boolean isRead;
    private Instant readAt;
    private Instant expiresAt;
    private Instant createdAt;
}
