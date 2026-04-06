package pe.edu.fineflow.profile.domain.model;

import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guardian {
    private String id;
    private String schoolId;
    private String userId;
    private String studentId;
    private String firstName;
    private String lastName;
    private String relationship;
    private String phone;
    private String documentNumber;
    private String email;
    private boolean isPrimaryContact;
    private Instant createdAt;
}
