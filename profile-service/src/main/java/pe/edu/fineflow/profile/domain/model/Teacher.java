package pe.edu.fineflow.profile.domain.model;

import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private String id;
    private String schoolId;
    private String userId;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String specialty;
    private String status;
    private Instant createdAt;
}
