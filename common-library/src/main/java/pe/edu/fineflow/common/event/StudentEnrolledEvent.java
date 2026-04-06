package pe.edu.fineflow.common.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public final class StudentEnrolledEvent extends DomainEvent {
    private final String studentId;
    private final String sectionId;

    public StudentEnrolledEvent(String schoolId, String triggeredBy, String studentId, String sectionId) {
        super(schoolId, triggeredBy);
        this.studentId = studentId;
        this.sectionId = sectionId;
    }

    @Override public String getEventType() { return "STUDENT_ENROLLED"; }
}
