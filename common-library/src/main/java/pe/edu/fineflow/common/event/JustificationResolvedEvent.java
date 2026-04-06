package pe.edu.fineflow.common.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public final class JustificationResolvedEvent extends DomainEvent {
    private final String justificationId;
    private final String studentId;
    private final String resolution;

    public JustificationResolvedEvent(String schoolId, String triggeredBy, 
            String justificationId, String studentId, String resolution) {
        super(schoolId, triggeredBy);
        this.justificationId = justificationId; 
        this.studentId = studentId; 
        this.resolution = resolution;
    }

    @Override public String getEventType() { return "JUSTIFICATION_RESOLVED"; }
}
