package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("CLASS_TASKS")
public class ClassTaskEntity {
    @Id
    @Column("ID")
    private String id;
    @Column("SCHOOL_ID")
    private String schoolId;
    @Column("COURSE_ASSIGNMENT_ID")
    private String courseAssignmentId;
    @Column("COMPETENCY_ID")
    private String competencyId;
    @Column("ACADEMIC_PERIOD_ID")
    private String academicPeriodId;
    @Column("TITLE")
    private String title;
    @Column("DESCRIPTION")
    private String description;
    @Column("TASK_TYPE")
    private String taskType;
    @Column("MAX_SCORE")
    private BigDecimal maxScore;
    @Column("DUE_DATE")
    private LocalDate dueDate;
    @Column("IS_ACTIVE")
    private Integer isActive;
    @Column("CREATED_AT")
    private Instant createdAt;
}
