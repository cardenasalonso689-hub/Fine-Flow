package pe.edu.fineflow.support.infrastructure.adapter.out.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Table("NOTIFICATIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity {
    @Id
    private String id;
    @Column("SCHOOL_ID")         private String schoolId;
    @Column("USER_ID")            private String userId;
    @Column("TARGET_ROLE")        private String targetRole;
    @Column("NOTIFICATION_TYPE")  private String notificationType;
    @Column("TITLE")              private String title;
    @Column("BODY")               private String body;
    @Column("ACTION_URL")         private String actionUrl;
    @Column("METADATA_JSON")      private String metadataJson;
    @Column("IS_READ")            private boolean isRead;
    @Column("READ_AT")            private Instant readAt;
    @Column("EXPIRES_AT")         private Instant expiresAt;
    @CreatedDate @Column("CREATED_AT") private Instant createdAt;
}
