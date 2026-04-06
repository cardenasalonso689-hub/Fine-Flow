package pe.edu.fineflow.academic.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AcademicLevelDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @NotBlank(message = "El nombre es requerido")
        private String name;
        @NotNull(message = "El orden es requerido")
        private Integer orderNum;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        @NotBlank(message = "El nombre es requerido")
        private String name;
        @NotNull(message = "El orden es requerido")
        private Integer orderNum;
        private Integer isActive;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String id;
        private String name;
        private Integer orderNum;
        private Integer isActive;
    }
}
