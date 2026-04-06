package pe.edu.fineflow.academic.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseAssignment {
    private String id;
    private String schoolId;
    private String courseId;
    private String sectionId;
    private String teacherId;
    private String academicPeriodId;
    private Integer hoursPerWeek;
    private Integer isActive;
    private Instant createdAt;
}
