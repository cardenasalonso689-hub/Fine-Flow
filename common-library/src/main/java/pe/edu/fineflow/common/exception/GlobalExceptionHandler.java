package pe.edu.fineflow.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Mono<ResponseEntity<ErrorResponseDto>> handleBusiness(BusinessException ex, ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.status(ex.getStatus()).body(
                ErrorResponseDto.of(ex.getStatus().value(), ex.getCode(), ex.getMessage(),
                        exchange.getRequest().getPath().value())));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ErrorResponseDto>> handleValidation(WebExchangeBindException ex,
            ServerWebExchange exchange) {
        List<ErrorResponseDto.FieldErrorDto> fieldErrors = ex.getBindingResult()
                .getAllErrors().stream()
                .filter(e -> e instanceof FieldError)
                .map(e -> (FieldError) e)
                .map(fe -> new ErrorResponseDto.FieldErrorDto(fe.getField(), fe.getDefaultMessage()))
                .toList();
        return Mono.just(ResponseEntity.badRequest().body(
                ErrorResponseDto.withFields(400, "VALIDATION_FAILED", "Error de validación",
                        exchange.getRequest().getPath().value(), fieldErrors)));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponseDto>> handleGeneral(Exception ex, ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.internalServerError().body(
                ErrorResponseDto.of(500, "INTERNAL_ERROR", "Error interno del servidor",
                        exchange.getRequest().getPath().value())));
    }
}
