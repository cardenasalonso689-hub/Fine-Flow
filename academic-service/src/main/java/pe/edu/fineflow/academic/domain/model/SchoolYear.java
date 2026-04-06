package pe.edu.fineflow.academic.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolYear {
    private String id;
    private String schoolId;
    private String academicLevelId;
    private String name;
    private Integer gradeNumber;
    private Integer calendarYear;
    private Integer isActive;
    private Instant createdAt;
}
