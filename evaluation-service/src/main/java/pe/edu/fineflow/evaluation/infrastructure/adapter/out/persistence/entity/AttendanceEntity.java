package pe.edu.fineflow.evaluation.infrastructure.adapter.out.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Table("ATTENDANCES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceEntity {
    @Id
    private String id;
    @Column("SCHOOL_ID")              private String schoolId;
    @Column("STUDENT_ID")             private String studentId;
    @Column("COURSE_ASSIGNMENT_ID")   private String courseAssignmentId;
    @Column("ATTENDANCE_DATE")        private LocalDate attendanceDate;
    @Column("STATUS")                 private String status;
    @Column("CHECK_IN_TIME")          private String checkInTime;
    @Column("RECORD_METHOD")          private String recordMethod;
    @Column("JUSTIFICATION_REASON")   private String justificationReason;
    @Column("REGISTERED_BY")          private String registeredBy;
    @CreatedDate @Column("CREATED_AT") private Instant createdAt;
}
