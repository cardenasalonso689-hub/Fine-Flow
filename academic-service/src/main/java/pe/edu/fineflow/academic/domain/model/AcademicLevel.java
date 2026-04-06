package pe.edu.fineflow.academic.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicLevel {
    private String id;
    private String schoolId;
    private String name;
    private Integer orderNum;
    private Integer isActive;
    private Instant createdAt;
}
