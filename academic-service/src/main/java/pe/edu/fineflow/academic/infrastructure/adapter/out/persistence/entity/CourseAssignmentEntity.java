package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("COURSE_ASSIGNMENTS")
public class CourseAssignmentEntity {
    @Id
    @Column("ID")
    private String id;
    @Column("SCHOOL_ID")
    private String schoolId;
    @Column("COURSE_ID")
    private String courseId;
    @Column("SECTION_ID")
    private String sectionId;
    @Column("TEACHER_ID")
    private String teacherId;
    @Column("ACADEMIC_PERIOD_ID")
    private String academicPeriodId;
    @Column("HOURS_PER_WEEK")
    private Integer hoursPerWeek;
    @Column("IS_ACTIVE")
    private Integer isActive;
    @Column("CREATED_AT")
    private Instant createdAt;
}
