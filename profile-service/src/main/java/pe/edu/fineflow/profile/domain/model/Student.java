package pe.edu.fineflow.profile.domain.model;
import java.time.LocalDate; import java.time.Instant;
public class Student {
    private String id, schoolId, sectionId, userId, firstName, lastName;
    private String documentType, documentNumber, bloodType, photoUrl, qrSecret, status;
    private LocalDate birthDate;
    private Instant createdAt, updatedAt;
    public String getId()            { return id; }
    public String getSchoolId()      { return schoolId; }
    public String getSectionId()     { return sectionId; }
    public String getFirstName()     { return firstName; }
    public String getLastName()      { return lastName; }
    public String getDocumentNumber(){ return documentNumber; }
    public String getStatus()        { return status; }
    public void setId(String v)     { this.id = v; }
    public void setSchoolId(String v){ this.schoolId = v; }
    public void setSectionId(String v){ this.sectionId = v; }
    public void setFirstName(String v){ this.firstName = v; }
    public void setLastName(String v){ this.lastName = v; }
    public void setDocumentType(String v){ this.documentType = v; }
    public void setDocumentNumber(String v){ this.documentNumber = v; }
    public void setBirthDate(LocalDate v){ this.birthDate = v; }
    public void setStatus(String v)  { this.status = v; }
    public void setCreatedAt(Instant v){ this.createdAt = v; }
}
