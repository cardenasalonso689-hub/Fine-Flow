package pe.edu.fineflow.evaluation.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentScore {
    private String id;
    private String schoolId;
    private String studentId;
    private String classTaskId;
    private String registeredBy;
    private String comments;
    private BigDecimal score;
    private Instant registeredAt;
    private Instant updatedAt;
}