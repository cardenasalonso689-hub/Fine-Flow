package pe.edu.fineflow.common.event;
import java.time.Instant;
import java.util.UUID;
public abstract class DomainEvent {
    private final String eventId;
    private final String schoolId;
    private final String triggeredBy;
    private final Instant occurredAt;
    protected DomainEvent(String schoolId, String triggeredBy) {
        this.eventId = UUID.randomUUID().toString();
        this.schoolId = schoolId;
        this.triggeredBy = triggeredBy;
        this.occurredAt = Instant.now();
    }
    public String getEventId()     { return eventId; }
    public String getSchoolId()    { return schoolId; }
    public String getTriggeredBy() { return triggeredBy; }
    public Instant getOccurredAt() { return occurredAt; }
    public abstract String getEventType();
}
