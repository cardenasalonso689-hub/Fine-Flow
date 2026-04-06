package pe.edu.fineflow.innovation.infrastructure.adapter.out.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Table("CHAT_SESSIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatSessionEntity {
    @Id
    private String id;
    @Column("SCHOOL_ID")     private String schoolId;
    @Column("USER_ID")       private String userId;
    @Column("USER_ROLE")      private String userRole;
    @Column("SESSION_TOKEN") private String sessionToken;
    @Column("STARTED_AT")    private Instant startedAt;
    @Column("LAST_MESSAGE_AT") private Instant lastMessageAt;
    @Column("ENDED_AT")      private Instant endedAt;
    @Column("IS_ACTIVE")     private boolean isActive;
}
