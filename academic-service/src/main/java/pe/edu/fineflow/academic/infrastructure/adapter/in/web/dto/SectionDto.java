package pe.edu.fineflow.academic.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class SectionDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @NotBlank(message = "El nombre es requerido")
        private String name;
        @NotNull(message = "La capacidad máxima es requerida")
        private Integer maxCapacity;
        @NotBlank(message = "El año escolar es requerido")
        private String schoolYearId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        @NotBlank(message = "El nombre es requerido")
        private String name;
        @NotNull(message = "La capacidad máxima es requerida")
        private Integer maxCapacity;
        private String tutorId;
        private Integer isActive;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String id;
        private String name;
        private Integer maxCapacity;
        private String tutorId;
        private Integer isActive;
    }
}
