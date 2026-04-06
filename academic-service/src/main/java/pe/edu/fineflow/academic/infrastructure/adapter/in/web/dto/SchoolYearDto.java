package pe.edu.fineflow.academic.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

public class SchoolYearDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @NotBlank(message = "El nombre es requerido")
        private String name;
        @NotNull(message = "El grado es requerido")
        private Integer gradeNumber;
        @NotNull(message = "El año calendario es requerido")
        private Integer calendarYear;
        @NotBlank(message = "El nivel académico es requerido")
        private String academicLevelId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        @NotBlank(message = "El nombre es requerido")
        private String name;
        @NotNull(message = "El grado es requerido")
        private Integer gradeNumber;
        @NotNull(message = "El año calendario es requerido")
        private Integer calendarYear;
        @NotBlank(message = "El nivel académico es requerido")
        private String academicLevelId;
        private Integer isActive;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String id;
        private String name;
        private Integer gradeNumber;
        private Integer calendarYear;
        private String academicLevelId;
        private Integer isActive;
    }
}
