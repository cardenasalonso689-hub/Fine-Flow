package pe.edu.fineflow.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_CREDENTIALS("AUTH_001", "Credenciales incorrectas", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED("AUTH_002", "El token ha expirado", HttpStatus.UNAUTHORIZED),
    TOKEN_INVALID("AUTH_003", "Token inválido o malformado", HttpStatus.UNAUTHORIZED),
    TOKEN_REVOKED("AUTH_004", "El token ha sido revocado", HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED("AUTH_005", "No tiene permisos para esta operación", HttpStatus.FORBIDDEN),
    ACCOUNT_LOCKED("AUTH_006", "Cuenta bloqueada por demasiados intentos fallidos", HttpStatus.LOCKED),
    TENANT_NOT_FOUND("TENANT_001", "Institución educativa no encontrada", HttpStatus.NOT_FOUND),
    TENANT_INACTIVE("TENANT_002", "La institución está inactiva o suspendida", HttpStatus.FORBIDDEN),
    TENANT_MISSING("TENANT_003", "No se pudo determinar la institución del request", HttpStatus.BAD_REQUEST),
    SUBSCRIPTION_EXPIRED("TENANT_004", "La suscripción del colegio ha expirado", HttpStatus.PAYMENT_REQUIRED),
    RESOURCE_NOT_FOUND("RES_001", "Recurso no encontrado", HttpStatus.NOT_FOUND),
    RESOURCE_ALREADY_EXISTS("RES_002", "El recurso ya existe", HttpStatus.CONFLICT),
    INVALID_REQUEST("RES_003", "Datos de la solicitud inválidos", HttpStatus.BAD_REQUEST),
    OPERATION_NOT_ALLOWED("RES_004", "Operación no permitida en el estado actual", HttpStatus.UNPROCESSABLE_ENTITY),
    SECTION_FULL("ACAD_001", "La sección ha alcanzado su capacidad máxima", HttpStatus.CONFLICT),
    PERIOD_OVERLAP("ACAD_002", "Ya existe un período académico activo para este año", HttpStatus.CONFLICT),
    SCORE_OUT_OF_RANGE("ACAD_003", "La nota debe estar entre 0 y 20", HttpStatus.BAD_REQUEST),
    BLOCKCHAIN_IMMUTABLE("BC_001", "Los bloques del blockchain no pueden ser modificados", HttpStatus.FORBIDDEN),
    INTERNAL_ERROR("SRV_001", "Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String defaultMessage;
    private final org.springframework.http.HttpStatus httpStatus;
}
