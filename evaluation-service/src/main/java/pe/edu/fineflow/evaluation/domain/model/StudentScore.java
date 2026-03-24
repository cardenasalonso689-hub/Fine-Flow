package pe.edu.fineflow.evaluation.domain.model;
import java.math.BigDecimal; import java.time.Instant;
public class StudentScore {
    private String id, schoolId, studentId, classTaskId, registeredBy, comments;
    private BigDecimal score;
    private Instant registeredAt, updatedAt;
    public String getId()          { return id; }
    public String getSchoolId()    { return schoolId; }
    public String getStudentId()   { return studentId; }
    public String getClassTaskId() { return classTaskId; }
    public BigDecimal getScore()   { return score; }
    public void setId(String v)    { this.id = v; }
    public void setSchoolId(String v){ this.schoolId = v; }
    public void setStudentId(String v){ this.studentId = v; }
    public void setClassTaskId(String v){ this.classTaskId = v; }
    public void setScore(BigDecimal v){ this.score = v; }
    public void setComments(String v){ this.comments = v; }
    public void setRegisteredBy(String v){ this.registeredBy = v; }
    public void setRegisteredAt(Instant v){ this.registeredAt = v; }
}
