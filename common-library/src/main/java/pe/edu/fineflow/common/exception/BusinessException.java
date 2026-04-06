package pe.edu.fineflow.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import pe.edu.fineflow.common.enums.ErrorCode;

@Getter
public class BusinessException extends RuntimeException {

    private final String code;
    private final HttpStatus status;

    public BusinessException(String code, String message, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getDefaultMessage());
        this.code = errorCode.getCode();
        this.status = errorCode.getHttpStatus();
    }

    public BusinessException(ErrorCode errorCode, String detail) {
        super(detail);
        this.code = errorCode.getCode();
        this.status = errorCode.getHttpStatus();
    }

    public static BusinessException badRequest(String code, String msg) {
        return new BusinessException(code, msg, HttpStatus.BAD_REQUEST);
    }

    public static BusinessException conflict(String code, String msg) {
        return new BusinessException(code, msg, HttpStatus.CONFLICT);
    }

    public static BusinessException forbidden(String code, String msg) {
        return new BusinessException(code, msg, HttpStatus.FORBIDDEN);
    }

    public static BusinessException notFound(String code, String msg) {
        return new BusinessException(code, msg, HttpStatus.NOT_FOUND);
    }
}
