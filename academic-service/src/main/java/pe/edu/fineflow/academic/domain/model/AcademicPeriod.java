package pe.edu.fineflow.academic.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicPeriod {
    private String id;
    private String schoolId;
    private String schoolYearId;
    private String name;
    private String periodType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer isActive;
    private Instant createdAt;
}
