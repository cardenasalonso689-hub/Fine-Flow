package pe.edu.fineflow.common.exception;

import pe.edu.fineflow.common.enums.ErrorCode;

/**
 * Violación del aislamiento multi-tenant:
 * - Colegio no encontrado o inactivo
 * - school_id ausente en el contexto reactivo
 */
public class TenantException extends BusinessException {

    public TenantException(ErrorCode code) {
        super(code);
    }

    public TenantException(ErrorCode code, String detail) {
        super(code, detail);
    }

    public static TenantException missing() {
        return new TenantException(ErrorCode.TENANT_MISSING);
    }

    public static TenantException inactive(String schoolId) {
        return new TenantException(ErrorCode.TENANT_INACTIVE,
            "El colegio '" + schoolId + "' está inactivo o suspendido");
    }
}
