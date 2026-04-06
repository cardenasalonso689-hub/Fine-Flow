package pe.edu.fineflow.academic.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Section {
    private String id;
    private String schoolId;
    private String schoolYearId;
    private String name;
    private Integer maxCapacity;
    private String tutorId;
    private Integer isActive;
    private Instant createdAt;
}
