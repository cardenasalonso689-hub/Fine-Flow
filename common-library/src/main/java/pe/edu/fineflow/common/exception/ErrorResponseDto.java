package pe.edu.fineflow.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponseDto(
        int status,
        String code,
        String message,
        String path,
        Instant timestamp,
        List<FieldErrorDto> fieldErrors) {
    public record FieldErrorDto(String field, String message) {
    }

    public static ErrorResponseDto of(int status, String code, String message, String path) {
        return new ErrorResponseDto(status, code, message, path, Instant.now(), null);
    }

    public static ErrorResponseDto withFields(int status, String code, String message,
            String path, List<FieldErrorDto> fieldErrors) {
        return new ErrorResponseDto(status, code, message, path, Instant.now(), fieldErrors);
    }
}
