package pe.edu.fineflow.academic.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassTask {
    private String id;
    private String schoolId;
    private String courseAssignmentId;
    private String competencyId;
    private String academicPeriodId;
    private String title;
    private String description;
    private String taskType;
    private BigDecimal maxScore;
    private LocalDate dueDate;
    private Integer isActive;
    private Instant createdAt;
}
