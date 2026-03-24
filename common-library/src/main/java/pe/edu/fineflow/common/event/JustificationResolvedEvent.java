package pe.edu.fineflow.common.event;
public final class JustificationResolvedEvent extends DomainEvent {
    private final String justificationId, studentId, resolution;
    public JustificationResolvedEvent(String schoolId, String triggeredBy, String justificationId, String studentId, String resolution) {
        super(schoolId, triggeredBy); this.justificationId = justificationId; this.studentId = studentId; this.resolution = resolution;
    }
    @Override public String getEventType()  { return "JUSTIFICATION_RESOLVED"; }
    public String getJustificationId()      { return justificationId; }
    public String getStudentId()            { return studentId; }
    public String getResolution()           { return resolution; }
}
