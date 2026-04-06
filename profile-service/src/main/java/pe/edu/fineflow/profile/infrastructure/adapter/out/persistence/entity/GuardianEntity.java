package pe.edu.fineflow.profile.infrastructure.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("GUARDIANS")
public class GuardianEntity {
    @Id
    @Column("ID")
    private String id;
    @Column("SCHOOL_ID")
    private String schoolId;
    @Column("USER_ID")
    private String userId;
    @Column("STUDENT_ID")
    private String studentId;
    @Column("FIRST_NAME")
    private String firstName;
    @Column("LAST_NAME")
    private String lastName;
    @Column("DOCUMENT_NUMBER")
    private String documentNumber;
    @Column("RELATIONSHIP")
    private String relationship;
    @Column("PHONE")
    private String phone;
    @Column("EMAIL")
    private String email;
    @Column("IS_PRIMARY_CONTACT")
    private Integer isPrimaryContact;
    @Column("CREATED_AT")
    private Instant createdAt;
}
