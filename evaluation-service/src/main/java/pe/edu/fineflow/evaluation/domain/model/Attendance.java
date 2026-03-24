package pe.edu.fineflow.evaluation.domain.model;
import java.time.LocalDate; import java.time.Instant;
public class Attendance {
    private String id, schoolId, studentId, courseAssignmentId;
    private LocalDate attendanceDate;
    private String status, checkInTime, recordMethod, justificationReason, registeredBy;
    private Instant createdAt;
    public String getId()                  { return id; }
    public String getSchoolId()            { return schoolId; }
    public String getStudentId()           { return studentId; }
    public String getCourseAssignmentId()  { return courseAssignmentId; }
    public LocalDate getAttendanceDate()   { return attendanceDate; }
    public String getStatus()              { return status; }
    public String getRecordMethod()        { return recordMethod; }
    public void setId(String v)            { this.id = v; }
    public void setSchoolId(String v)      { this.schoolId = v; }
    public void setStudentId(String v)     { this.studentId = v; }
    public void setCourseAssignmentId(String v){ this.courseAssignmentId = v; }
    public void setAttendanceDate(LocalDate v){ this.attendanceDate = v; }
    public void setStatus(String v)        { this.status = v; }
    public void setCheckInTime(String v)   { this.checkInTime = v; }
    public void setRecordMethod(String v)  { this.recordMethod = v; }
    public void setRegisteredBy(String v)  { this.registeredBy = v; }
    public void setCreatedAt(Instant v)    { this.createdAt = v; }
}
