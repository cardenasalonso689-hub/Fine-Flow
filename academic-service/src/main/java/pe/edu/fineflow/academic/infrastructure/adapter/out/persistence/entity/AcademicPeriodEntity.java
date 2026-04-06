package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("ACADEMIC_PERIODS")
public class AcademicPeriodEntity {
    @Id
    @Column("ID")
    private String id;
    @Column("SCHOOL_ID")
    private String schoolId;
    @Column("SCHOOL_YEAR_ID")
    private String schoolYearId;
    @Column("NAME")
    private String name;
    @Column("PERIOD_TYPE")
    private String periodType;
    @Column("START_DATE")
    private LocalDate startDate;
    @Column("END_DATE")
    private LocalDate endDate;
    @Column("IS_ACTIVE")
    private Integer isActive;
    @Column("CREATED_AT")
    private Instant createdAt;
}
