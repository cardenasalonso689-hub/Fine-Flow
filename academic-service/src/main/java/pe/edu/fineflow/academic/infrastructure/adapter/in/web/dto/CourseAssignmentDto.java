package pe.edu.fineflow.academic.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CourseAssignmentDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @NotBlank(message = "El curso es requerido")
        private String courseId;
        @NotBlank(message = "La sección es requerida")
        private String sectionId;
        @NotBlank(message = "El docente es requerido")
        private String teacherId;
        @NotBlank(message = "El período académico es requerido")
        private String academicPeriodId;
        private Integer hoursPerWeek;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        @NotBlank(message = "El curso es requerido")
        private String courseId;
        @NotBlank(message = "La sección es requerida")
        private String sectionId;
        @NotBlank(message = "El docente es requerido")
        private String teacherId;
        @NotBlank(message = "El período académico es requerido")
        private String academicPeriodId;
        private Integer hoursPerWeek;
        private Integer isActive;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String id;
        private String courseId;
        private String sectionId;
        private String teacherId;
        private String academicPeriodId;
        private Integer hoursPerWeek;
        private Integer isActive;
    }
}
