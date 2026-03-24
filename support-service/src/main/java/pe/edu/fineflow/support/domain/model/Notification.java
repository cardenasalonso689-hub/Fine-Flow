package pe.edu.fineflow.support.domain.model;
import java.time.Instant;
public class Notification {
    private String id, schoolId, userId, targetRole, notificationType, title, body, actionUrl, metadataJson;
    private boolean isRead;
    private Instant readAt, expiresAt, createdAt;
    public String getId()              { return id; }
    public String getSchoolId()        { return schoolId; }
    public String getUserId()          { return userId; }
    public String getTargetRole()      { return targetRole; }
    public String getNotificationType(){ return notificationType; }
    public String getTitle()           { return title; }
    public String getBody()            { return body; }
    public boolean isRead()            { return isRead; }
    public void setId(String v)        { this.id = v; }
    public void setSchoolId(String v)  { this.schoolId = v; }
    public void setUserId(String v)    { this.userId = v; }
    public void setTargetRole(String v){ this.targetRole = v; }
    public void setNotificationType(String v){ this.notificationType = v; }
    public void setTitle(String v)     { this.title = v; }
    public void setBody(String v)      { this.body = v; }
    public void setActionUrl(String v) { this.actionUrl = v; }
    public void setRead(boolean v)     { this.isRead = v; }
    public void setCreatedAt(Instant v){ this.createdAt = v; }
}
