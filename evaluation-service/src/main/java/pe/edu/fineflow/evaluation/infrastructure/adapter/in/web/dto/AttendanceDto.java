package pe.edu.fineflow.evaluation.infrastructure.adapter.in.web.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate; import java.time.Instant;
public class AttendanceDto {
    private AttendanceDto() {}
    public record Create(@NotBlank String studentId, String courseAssignmentId, @NotNull LocalDate attendanceDate, @NotBlank String status, String checkInTime, String recordMethod) {}
    public record Response(String id, String schoolId, String studentId, String courseAssignmentId, LocalDate attendanceDate, String status, String checkInTime, String recordMethod, Instant createdAt) {}
}
