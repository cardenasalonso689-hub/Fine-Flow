package pe.edu.fineflow.profile.infrastructure.adapter.in.web.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;

public class StudentDto {
    private StudentDto() {}

    public record Create(
        @NotBlank @Size(max=100) String firstName,
        @NotBlank @Size(max=100) String lastName,
        @NotBlank @Size(min=8, max=20) String documentNumber,
        String documentType,
        LocalDate birthDate,
        String sectionId
    ) {}

    public record Update(
        @NotBlank String firstName,
        @NotBlank String lastName,
        String sectionId,
        String status
    ) {}

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Response(
        String  id,
        String  schoolId,
        String  sectionId,
        String  firstName,
        String  lastName,
        String  documentType,
        String  documentNumber,
        LocalDate birthDate,
        String  status,
        Instant createdAt
    ) {}
}
