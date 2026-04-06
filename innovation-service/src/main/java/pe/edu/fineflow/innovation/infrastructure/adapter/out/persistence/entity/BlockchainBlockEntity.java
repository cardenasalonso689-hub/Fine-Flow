package pe.edu.fineflow.innovation.infrastructure.adapter.out.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Table("BLOCKCHAIN_BLOCKS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockchainBlockEntity {
    @Id
    private String id;
    @Column("SCHOOL_ID")     private String schoolId;
    @Column("EVENT_TYPE")    private String eventType;
    @Column("ENTITY_ID")     private String entityId;
    @Column("ENTITY_TYPE")   private String entityType;
    @Column("PAYLOAD")       private String payload;
    @Column("PREVIOUS_HASH") private String previousHash;
    @Column("HASH")          private String hash;
    @Column("CREATED_BY")    private String createdBy;
    @Column("BLOCK_INDEX")   private int blockIndex;
    @CreatedDate @Column("CREATED_AT") private Instant createdAt;
}
