package pe.edu.fineflow.innovation.infrastructure.adapter.out.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Table("CHAT_MESSAGES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageEntity {
    @Id
    private String id;
    @Column("SESSION_ID")      private String sessionId;
    @Column("ROLE")            private String role;
    @Column("CONTENT")         private String content;
    @Column("SOURCES_JSON")    private String sourcesJson;
    @Column("CONFIDENCE")      private BigDecimal confidence;
    @Column("CREATED_AT")      private Instant createdAt;
}
