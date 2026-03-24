package pe.edu.fineflow.common.event;
public final class StudentEnrolledEvent extends DomainEvent {
    private final String studentId, sectionId;
    public StudentEnrolledEvent(String schoolId, String triggeredBy, String studentId, String sectionId) {
        super(schoolId, triggeredBy); this.studentId = studentId; this.sectionId = sectionId;
    }
    @Override public String getEventType() { return "STUDENT_ENROLLED"; }
    public String getStudentId() { return studentId; }
    public String getSectionId() { return sectionId; }
}
