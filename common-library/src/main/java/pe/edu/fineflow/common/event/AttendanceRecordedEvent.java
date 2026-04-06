package pe.edu.fineflow.common.event;

import lombok.Getter;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Getter
public final class AttendanceRecordedEvent extends DomainEvent {
    private final String attendanceId;
    private final String studentId;
    private final String status;
    private final String attendanceDate;

    public AttendanceRecordedEvent(String schoolId, String triggeredBy, String attendanceId, 
            String studentId, String status, String attendanceDate) {
        super(schoolId, triggeredBy);
        this.attendanceId = attendanceId; 
        this.studentId = studentId; 
        this.status = status; 
        this.attendanceDate = attendanceDate;
    }

    @Override public String getEventType() { return "ATTENDANCE_RECORDED"; }
}
