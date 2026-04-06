package pe.edu.fineflow.profile.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class GuardianDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @NotBlank(message = "El nombre es requerido")
        private String firstName;
        @NotBlank(message = "El apellido es requerido")
        private String lastName;
        @NotBlank(message = "La relación es requerida")
        private String relationship;
        private String phone;
        private String documentNumber;
        private String email;
        private boolean primaryContact;
        @NotBlank(message = "El estudiante es requerido")
        private String studentId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        @NotBlank(message = "El nombre es requerido")
        private String firstName;
        @NotBlank(message = "El apellido es requerido")
        private String lastName;
        @NotBlank(message = "La relación es requerida")
        private String relationship;
        private String phone;
        private String documentNumber;
        private String email;
        private boolean primaryContact;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String id;
        private String firstName;
        private String lastName;
        private String relationship;
        private String phone;
        private String documentNumber;
        private String email;
        private boolean primaryContact;
        private String studentId;
    }
}
