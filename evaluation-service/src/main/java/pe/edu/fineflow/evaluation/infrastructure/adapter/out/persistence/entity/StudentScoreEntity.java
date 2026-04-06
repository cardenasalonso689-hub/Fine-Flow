package pe.edu.fineflow.evaluation.infrastructure.adapter.out.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Table("STUDENT_SCORES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentScoreEntity {
    @Id
    private String id;
    @Column("SCHOOL_ID")        private String schoolId;
    @Column("STUDENT_ID")       private String studentId;
    @Column("CLASS_TASK_ID")    private String classTaskId;
    @Column("SCORE")           private BigDecimal score;
    @Column("COMMENTS")        private String comments;
    @Column("REGISTERED_BY")    private String registeredBy;
    @CreatedDate          @Column("REGISTERED_AT")  private Instant registeredAt;
    @CreatedDate          @Column("UPDATED_AT")    private Instant updatedAt;
}
