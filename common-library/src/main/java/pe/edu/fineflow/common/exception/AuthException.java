package pe.edu.fineflow.common.exception;

import pe.edu.fineflow.common.enums.ErrorCode;

/**
 * Errores de autenticación y autorización.
 */
public class AuthException extends BusinessException {

    public AuthException(ErrorCode code) {
        super(code);
    }

    public AuthException(ErrorCode code, String detail) {
        super(code, detail);
    }

    public static AuthException invalidCredentials() {
        return new AuthException(ErrorCode.INVALID_CREDENTIALS);
    }

    public static AuthException tokenExpired() {
        return new AuthException(ErrorCode.TOKEN_EXPIRED);
    }

    public static AuthException tokenInvalid() {
        return new AuthException(ErrorCode.TOKEN_INVALID);
    }

    public static AuthException accountLocked() {
        return new AuthException(ErrorCode.ACCOUNT_LOCKED);
    }
}
