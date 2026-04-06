package pe.edu.fineflow.innovation.domain.model;

import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockchainBlock {
    private String id;
    private String schoolId;
    private String eventType;
    private String entityId;
    private String entityType;
    private String payload;
    private String previousHash;
    private String hash;
    private String createdBy;
    private int blockIndex;
    private Instant createdAt;
}
