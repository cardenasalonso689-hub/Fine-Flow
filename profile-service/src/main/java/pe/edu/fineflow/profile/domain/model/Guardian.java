package pe.edu.fineflow.profile.domain.model;
import java.time.Instant;
public class Guardian {
    private String id, schoolId, userId, studentId, firstName, lastName;
    private String relationship, phone, documentNumber;
    private boolean isPrimaryContact;
    private Instant createdAt;
    public String getId()        { return id; }
    public String getSchoolId()  { return schoolId; }
    public String getStudentId() { return studentId; }
    public void setId(String v)  { this.id = v; }
    public void setSchoolId(String v){ this.schoolId = v; }
    public void setStudentId(String v){ this.studentId = v; }
    public void setFirstName(String v){ this.firstName = v; }
    public void setLastName(String v){ this.lastName = v; }
    public void setRelationship(String v){ this.relationship = v; }
    public void setPhone(String v){ this.phone = v; }
    public void setCreatedAt(Instant v){ this.createdAt = v; }
}
