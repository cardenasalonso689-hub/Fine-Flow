package pe.edu.fineflow.academic.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

public class AcademicPeriodDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @NotBlank(message = "El nombre es requerido")
        private String name;
        @NotBlank(message = "El tipo de período es requerido")
        private String periodType;
        @NotNull(message = "La fecha de inicio es requerida")
        private LocalDate startDate;
        @NotNull(message = "La fecha de fin es requerida")
        private LocalDate endDate;
        @NotBlank(message = "El año escolar es requerido")
        private String schoolYearId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        @NotBlank(message = "El nombre es requerido")
        private String name;
        @NotBlank(message = "El tipo de período es requerido")
        private String periodType;
        @NotNull(message = "La fecha de inicio es requerida")
        private LocalDate startDate;
        @NotNull(message = "La fecha de fin es requerida")
        private LocalDate endDate;
        private Integer isActive;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String id;
        private String name;
        private String periodType;
        private LocalDate startDate;
        private LocalDate endDate;
        private Integer isActive;
    }
}
