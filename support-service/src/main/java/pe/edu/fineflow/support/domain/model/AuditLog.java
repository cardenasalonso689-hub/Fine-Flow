package pe.edu.fineflow.support.domain.model;
import java.time.Instant;
public class AuditLog {
    private String id, schoolId, userId, action, entityType, entityId;
    private String oldValueJson, newValueJson, ipAddress, userAgent, result, errorDetail;
    private Long durationMs;
    private Instant createdAt;
    public String getId()        { return id; }
    public String getSchoolId()  { return schoolId; }
    public String getAction()    { return action; }
    public String getEntityType(){ return entityType; }
    public String getEntityId()  { return entityId; }
    public String getResult()    { return result; }
    public void setId(String v)          { this.id = v; }
    public void setSchoolId(String v)    { this.schoolId = v; }
    public void setUserId(String v)      { this.userId = v; }
    public void setAction(String v)      { this.action = v; }
    public void setEntityType(String v)  { this.entityType = v; }
    public void setEntityId(String v)    { this.entityId = v; }
    public void setOldValueJson(String v){ this.oldValueJson = v; }
    public void setNewValueJson(String v){ this.newValueJson = v; }
    public void setIpAddress(String v)   { this.ipAddress = v; }
    public void setResult(String v)      { this.result = v; }
    public void setCreatedAt(Instant v)  { this.createdAt = v; }
}
