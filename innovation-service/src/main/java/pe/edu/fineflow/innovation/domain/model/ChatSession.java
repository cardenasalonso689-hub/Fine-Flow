package pe.edu.fineflow.innovation.domain.model;

import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatSession {
    private String id;
    private String schoolId;
    private String userId;
    private String userRole;
    private String sessionToken;
    private Instant startedAt;
    private Instant lastMessageAt;
    private Instant endedAt;
    private boolean isActive;
}
