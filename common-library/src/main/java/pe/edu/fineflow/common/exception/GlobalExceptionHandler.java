package pe.edu.fineflow.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Mono<ResponseEntity<ErrorResponseDto>> handleBusiness(
            BusinessException ex, ServerHttpRequest req) {
        var body = ErrorResponseDto.of(
                ex.getStatus().value(), ex.getCode(),
                ex.getMessage(), req.getPath().value());
        return Mono.just(ResponseEntity.status(ex.getStatus()).body(body));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ErrorResponseDto>> handleValidation(
            WebExchangeBindException ex, ServerHttpRequest req) {
        List<ErrorResponseDto.FieldErrorDto> fieldErrors = ex.getBindingResult()
                .getAllErrors().stream()
                .filter(e -> e instanceof FieldError)
                .map(e -> (FieldError) e)
                .map(fe -> new ErrorResponseDto.FieldErrorDto(fe.getField(), fe.getDefaultMessage()))
                .toList();
        var body = ErrorResponseDto.withFields(400, "VALIDATION_FAILED",
                "Error de validación en los campos.", req.getPath().value(), fieldErrors);
        return Mono.just(ResponseEntity.badRequest().body(body));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponseDto>> handleGeneral(
            Exception ex, ServerHttpRequest req) {
        var body = ErrorResponseDto.of(500, "INTERNAL_ERROR",
                "Error interno del servidor.", req.getPath().value());
        return Mono.just(ResponseEntity.internalServerError().body(body));
    }
}
