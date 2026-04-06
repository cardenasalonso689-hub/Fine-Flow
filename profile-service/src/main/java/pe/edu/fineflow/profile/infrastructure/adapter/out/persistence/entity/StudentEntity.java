package pe.edu.fineflow.profile.infrastructure.adapter.out.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Table("STUDENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {
    @Id
    private String id;
    @Column("SCHOOL_ID")       private String schoolId;
    @Column("SECTION_ID")       private String sectionId;
    @Column("USER_ID")          private String userId;
    @Column("FIRST_NAME")       private String firstName;
    @Column("LAST_NAME")        private String lastName;
    @Column("DOCUMENT_TYPE")    private String documentType;
    @Column("DOCUMENT_NUMBER")  private String documentNumber;
    @Column("BIRTH_DATE")       private LocalDate birthDate;
    @Column("BLOOD_TYPE")       private String bloodType;
    @Column("PHOTO_URL")        private String photoUrl;
    @Column("QR_SECRET")        private String qrSecret;
    @Column("STATUS")           private String status;
    @CreatedDate          @Column("CREATED_AT") private Instant createdAt;
    @LastModifiedDate     @Column("UPDATED_AT") private Instant updatedAt;
}
