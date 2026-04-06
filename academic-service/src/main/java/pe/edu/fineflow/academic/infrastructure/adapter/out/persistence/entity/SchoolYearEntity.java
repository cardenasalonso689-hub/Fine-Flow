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
@Table("SCHOOL_YEARS")
public class SchoolYearEntity {
    @Id
    @Column("ID")
    private String id;
    @Column("SCHOOL_ID")
    private String schoolId;
    @Column("ACADEMIC_LEVEL_ID")
    private String academicLevelId;
    @Column("NAME")
    private String name;
    @Column("GRADE_NUMBER")
    private Integer gradeNumber;
    @Column("CALENDAR_YEAR")
    private Integer calendarYear;
    @Column("IS_ACTIVE")
    private Integer isActive;
    @Column("CREATED_AT")
    private Instant createdAt;
}
