package pe.edu.fineflow.innovation.domain.model;
import java.time.Instant;
public class BlockchainBlock {
    private String id, schoolId, eventType, entityId, entityType;
    private String payload, previousHash, hash, createdBy;
    private int blockIndex;
    private Instant createdAt;
    public String getId()          { return id; }
    public String getSchoolId()    { return schoolId; }
    public String getEventType()   { return eventType; }
    public String getPreviousHash(){ return previousHash; }
    public String getHash()        { return hash; }
    public int    getBlockIndex()  { return blockIndex; }
    public void setId(String v)    { this.id = v; }
    public void setSchoolId(String v){ this.schoolId = v; }
    public void setBlockIndex(int v){ this.blockIndex = v; }
    public void setEventType(String v){ this.eventType = v; }
    public void setEntityId(String v){ this.entityId = v; }
    public void setEntityType(String v){ this.entityType = v; }
    public void setPayload(String v){ this.payload = v; }
    public void setPreviousHash(String v){ this.previousHash = v; }
    public void setHash(String v)  { this.hash = v; }
    public void setCreatedBy(String v){ this.createdBy = v; }
    public void setCreatedAt(Instant v){ this.createdAt = v; }
}
