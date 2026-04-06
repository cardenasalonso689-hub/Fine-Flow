package pe.edu.fineflow.common.event;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public abstract class DomainEvent {

    private final String eventId = UUID.randomUUID().toString();
    private final String schoolId;
    private final String triggeredBy;
    private final Instant occurredAt = Instant.now();

    protected DomainEvent(String schoolId, String triggeredBy) {
        this.schoolId = schoolId;
        this.triggeredBy = triggeredBy;
    }

    public abstract String getEventType();
}
