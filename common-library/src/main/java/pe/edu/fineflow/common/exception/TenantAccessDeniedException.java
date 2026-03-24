package pe.edu.fineflow.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Se lanza cuando un usuario intenta acceder a un recurso de otro colegio.
 * El API Gateway nunca debería dejar pasar esto, pero existe como defensa en profundidad.
 */
public class TenantAccessDeniedException extends BusinessException {
    public TenantAccessDeniedException(String schoolId) {
        super("TENANT_ACCESS_DENIED",
              "Acceso denegado: el recurso no pertenece al colegio '%s'.".formatted(schoolId),
              HttpStatus.FORBIDDEN);
    }
}
