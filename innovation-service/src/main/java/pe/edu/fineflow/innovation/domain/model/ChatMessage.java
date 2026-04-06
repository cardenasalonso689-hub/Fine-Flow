package pe.edu.fineflow.innovation.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String id;
    private String sessionId;
    private String role;
    private String content;
    private String sourcesJson;
    private BigDecimal confidence;
    private Instant createdAt;
}
