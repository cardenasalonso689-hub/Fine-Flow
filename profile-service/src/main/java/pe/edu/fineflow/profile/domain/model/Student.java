package pe.edu.fineflow.profile.domain.model;

import java.time.Instant;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String id;
    private String schoolId;
    private String sectionId;
    private String userId;
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private String bloodType;
    private String photoUrl;
    private String qrSecret;
    private String status;
    private LocalDate birthDate;
    private Instant createdAt;
    private Instant updatedAt;
}
