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
@Table("ACADEMIC_LEVELS")
public class AcademicLevelEntity {
    @Id
    @Column("ID")
    private String id;
    @Column("SCHOOL_ID")
    private String schoolId;
    @Column("NAME")
    private String name;
    @Column("ORDER_NUM")
    private Integer orderNum;
    @Column("IS_ACTIVE")
    private Integer isActive;
    @Column("CREATED_AT")
    private Instant createdAt;
}
