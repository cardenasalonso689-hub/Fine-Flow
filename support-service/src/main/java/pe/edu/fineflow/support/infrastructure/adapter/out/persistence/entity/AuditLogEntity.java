package pe.edu.fineflow.support.infrastructure.adapter.out.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Table("AUDIT_LOGS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogEntity {
    @Id
    private String id;
    @Column("SCHOOL_ID")      private String schoolId;
    @Column("USER_ID")        private String userId;
    @Column("ACTION")         private String action;
    @Column("ENTITY_TYPE")    private String entityType;
    @Column("ENTITY_ID")      private String entityId;
    @Column("OLD_VALUE_JSON") private String oldValueJson;
    @Column("NEW_VALUE_JSON") private String newValueJson;
    @Column("IP_ADDRESS")     private String ipAddress;
    @Column("USER_AGENT")     private String userAgent;
    @Column("RESULT")        private String result;
    @Column("ERROR_DETAIL")   private String errorDetail;
    @Column("DURATION_MS")    private Long durationMs;
    @CreatedDate @Column("CREATED_AT") private Instant createdAt;
}
