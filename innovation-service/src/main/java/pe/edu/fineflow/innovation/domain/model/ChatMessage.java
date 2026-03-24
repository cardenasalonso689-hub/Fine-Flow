package pe.edu.fineflow.innovation.domain.model;
import java.time.Instant;
import java.math.BigDecimal;
public class ChatMessage {
    private String id, sessionId, role, content, sourcesJson;
    private BigDecimal confidence;
    private Instant createdAt;
    public String getId()           { return id; }
    public String getSessionId()    { return sessionId; }
    public String getRole()         { return role; }
    public String getContent()      { return content; }
    public BigDecimal getConfidence(){ return confidence; }
    public void setId(String v)         { this.id = v; }
    public void setSessionId(String v)  { this.sessionId = v; }
    public void setRole(String v)       { this.role = v; }
    public void setContent(String v)    { this.content = v; }
    public void setSourcesJson(String v){ this.sourcesJson = v; }
    public void setConfidence(BigDecimal v){ this.confidence = v; }
    public void setCreatedAt(Instant v) { this.createdAt = v; }
}
