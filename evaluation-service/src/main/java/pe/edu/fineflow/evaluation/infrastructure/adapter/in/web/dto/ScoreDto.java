package pe.edu.fineflow.evaluation.infrastructure.adapter.in.web.dto;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal; import java.time.Instant;
public class ScoreDto {
    private ScoreDto() {}
    public record Create(@NotBlank String studentId, @NotBlank String classTaskId, @NotNull @DecimalMin("0") @DecimalMax("20") BigDecimal score, String comments) {}
    public record Update(@NotNull @DecimalMin("0") @DecimalMax("20") BigDecimal score, String comments) {}
    public record Response(String id, String schoolId, String studentId, String classTaskId, BigDecimal score, String comments, Instant registeredAt) {}
}
