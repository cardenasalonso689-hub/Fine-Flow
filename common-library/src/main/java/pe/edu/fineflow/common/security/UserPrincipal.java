package pe.edu.fineflow.common.security;

import java.util.Set;

/**
 * Objeto inmutable que representa al usuario autenticado extraído del JWT.
 * Se propaga a través del Reactor Context en cada request.
 */
public record UserPrincipal(
    String userId,
    String schoolId,
    String email,
    String role,
    Set<String> authorities
) {
    public boolean isAdmin()       { return "ADMIN".equals(role); }
    public boolean isCoordinator() { return "COORDINATOR".equals(role); }
    public boolean isTeacher()     { return "TEACHER".equals(role); }
    public boolean isStudent()     { return "STUDENT".equals(role); }
    public boolean isGuardian()    { return "GUARDIAN".equals(role); }

    /** Devuelve true si el recurso pertenece al mismo colegio del usuario. */
    public boolean ownsTenant(String resourceSchoolId) {
        return schoolId != null && schoolId.equals(resourceSchoolId);
    }
}
