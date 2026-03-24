package pe.edu.fineflow.common.event;
import java.math.BigDecimal;
public final class ScoreRegisteredEvent extends DomainEvent {
    private final String scoreId, studentId, classTaskId;
    private final BigDecimal score;
    public ScoreRegisteredEvent(String schoolId, String triggeredBy, String scoreId, String studentId, String classTaskId, BigDecimal score) {
        super(schoolId, triggeredBy);
        this.scoreId = scoreId; this.studentId = studentId; this.classTaskId = classTaskId; this.score = score;
    }
    @Override public String getEventType()  { return "SCORE_REGISTERED"; }
    public String getScoreId()     { return scoreId; }
    public String getStudentId()   { return studentId; }
    public String getClassTaskId() { return classTaskId; }
    public BigDecimal getScore()   { return score; }
}
