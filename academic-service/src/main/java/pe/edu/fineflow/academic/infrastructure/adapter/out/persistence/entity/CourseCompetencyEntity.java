package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("COURSE_COMPETENCIES")
public class CourseCompetencyEntity {
    @Id
    @Column("ID")
    private String id;
    @Column("SCHOOL_ID")
    private String schoolId;
    @Column("COURSE_ID")
    private String courseId;
    @Column("NAME")
    private String name;
    @Column("DESCRIPTION")
    private String description;
    @Column("WEIGHT")
    private BigDecimal weight;
    @Column("IS_ACTIVE")
    private Integer isActive;
    @Column("CREATED_AT")
    private Instant createdAt;
}
