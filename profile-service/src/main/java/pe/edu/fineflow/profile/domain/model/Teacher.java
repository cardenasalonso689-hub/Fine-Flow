package pe.edu.fineflow.profile.domain.model;
import java.time.Instant;
public class Teacher {
    private String id, schoolId, userId, firstName, lastName, documentNumber, specialty, status;
    private Instant createdAt;
    public String getId()            { return id; }
    public String getSchoolId()      { return schoolId; }
    public String getFirstName()     { return firstName; }
    public String getLastName()      { return lastName; }
    public String getDocumentNumber(){ return documentNumber; }
    public String getSpecialty()     { return specialty; }
    public String getStatus()        { return status; }
    public void setId(String v)     { this.id = v; }
    public void setSchoolId(String v){ this.schoolId = v; }
    public void setUserId(String v)  { this.userId = v; }
    public void setFirstName(String v){ this.firstName = v; }
    public void setLastName(String v){ this.lastName = v; }
    public void setDocumentNumber(String v){ this.documentNumber = v; }
    public void setSpecialty(String v){ this.specialty = v; }
    public void setStatus(String v)  { this.status = v; }
    public void setCreatedAt(Instant v){ this.createdAt = v; }
}
