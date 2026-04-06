package pe.edu.fineflow.academic.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ClassTaskDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @NotBlank(message = "El título es requerido")
        private String title;
        private String description;
        @NotBlank(message = "El tipo de tarea es requerido")
        private String taskType;
        @NotNull(message = "La nota máxima es requerida")
        private BigDecimal maxScore;
        private LocalDate dueDate;
        @NotBlank(message = "La asignación de curso es requerida")
        private String courseAssignmentId;
        @NotBlank(message = "La competencia es requerida")
        private String competencyId;
        @NotBlank(message = "El período académico es requerido")
        private String academicPeriodId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        @NotBlank(message = "El título es requerido")
        private String title;
        private String description;
        @NotBlank(message = "El tipo de tarea es requerido")
        private String taskType;
        @NotNull(message = "La nota máxima es requerida")
        private BigDecimal maxScore;
        private LocalDate dueDate;
        private Integer isActive;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String id;
        private String title;
        private String description;
        private String taskType;
        private BigDecimal maxScore;
        private LocalDate dueDate;
        private Integer isActive;
    }
}
