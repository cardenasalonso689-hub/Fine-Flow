package pe.edu.fineflow.innovation.domain.model;
import java.time.Instant;
public class ChatSession {
    private String id, schoolId, userId, userRole, sessionToken;
    private Instant startedAt, lastMessageAt, endedAt;
    private boolean isActive;
    public String getId()      { return id; }
    public String getSchoolId(){ return schoolId; }
    public String getUserId()  { return userId; }
    public boolean isActive()  { return isActive; }
    public void setId(String v)          { this.id = v; }
    public void setSchoolId(String v)    { this.schoolId = v; }
    public void setUserId(String v)      { this.userId = v; }
    public void setUserRole(String v)    { this.userRole = v; }
    public void setSessionToken(String v){ this.sessionToken = v; }
    public void setActive(boolean v)     { this.isActive = v; }
    public void setStartedAt(Instant v)  { this.startedAt = v; }
    public void setLastMessageAt(Instant v){ this.lastMessageAt = v; }
}
