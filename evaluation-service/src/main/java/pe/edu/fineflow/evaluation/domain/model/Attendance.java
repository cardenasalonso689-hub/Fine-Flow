package pe.edu.fineflow.evaluation.domain.model;

import java.time.Instant;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    private String id;
    private String schoolId;
    private String studentId;
    private String courseAssignmentId;
    private LocalDate attendanceDate;
    private String status;
    private String checkInTime;
    private String recordMethod;
    private String justificationReason;
    private String registeredBy;
    private Instant createdAt;
}
