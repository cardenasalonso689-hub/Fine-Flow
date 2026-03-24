package pe.edu.fineflow.profile.infrastructure.adapter.out.persistence.entity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.time.LocalDate;

@Table("STUDENTS")
public class StudentEntity {
    @Id
    private String id;
    @Column("SCHOOL_ID")   private String schoolId;
    @Column("SECTION_ID")  private String sectionId;
    @Column("USER_ID")     private String userId;
    @Column("FIRST_NAME")  private String firstName;
    @Column("LAST_NAME")   private String lastName;
    @Column("DOCUMENT_TYPE")   private String documentType;
    @Column("DOCUMENT_NUMBER") private String documentNumber;
    @Column("BIRTH_DATE")  private LocalDate birthDate;
    @Column("BLOOD_TYPE")  private String bloodType;
    @Column("STATUS")      private String status;
    @CreatedDate   @Column("CREATED_AT") private Instant createdAt;
    @LastModifiedDate @Column("UPDATED_AT") private Instant updatedAt;
    // All getters/setters
    public String getId()            { return id; }
    public String getSchoolId()      { return schoolId; }
    public String getSectionId()     { return sectionId; }
    public String getFirstName()     { return firstName; }
    public String getLastName()      { return lastName; }
    public String getDocumentNumber(){ return documentNumber; }
    public String getDocumentType()  { return documentType; }
    public String getStatus()        { return status; }
    public Instant getCreatedAt()    { return createdAt; }
    public void setId(String id)     { this.id = id; }
    public void setSchoolId(String s){ this.schoolId = s; }
    public void setSectionId(String s){ this.sectionId = s; }
    public void setFirstName(String s){ this.firstName = s; }
    public void setLastName(String s){ this.lastName = s; }
    public void setDocumentType(String s){ this.documentType = s; }
    public void setDocumentNumber(String s){ this.documentNumber = s; }
    public void setBirthDate(LocalDate d){ this.birthDate = d; }
    public void setStatus(String s)  { this.status = s; }
    public void setCreatedAt(Instant i){ this.createdAt = i; }
    public void setUpdatedAt(Instant i){ this.updatedAt = i; }
}
