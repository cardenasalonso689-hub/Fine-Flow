package pe.edu.fineflow.academic.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CourseDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @NotBlank(message = "El nombre es requerido")
        private String name;
        private String code;
        private String description;
        private String colorHex;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        @NotBlank(message = "El nombre es requerido")
        private String name;
        private String code;
        private String description;
        private String colorHex;
        private Integer isActive;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String id;
        private String name;
        private String code;
        private String description;
        private String colorHex;
        private Integer isActive;
    }
}
