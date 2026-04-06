package pe.edu.fineflow.common.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.time.Instant;

public final class ResponseUtils {

    private ResponseUtils() {}

    public static <T> Mono<ResponseEntity<T>> ok(T body) {
        return Mono.just(ResponseEntity.ok(body));
    }

    public static <T> Mono<ResponseEntity<T>> created(T body) {
        return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(body));
    }

    public static <T> Mono<ResponseEntity<T>> noContent() {
        return Mono.just(ResponseEntity.noContent().build());
    }

    public record ApiResponse<T>(T data, String message, Instant timestamp) {
        public static <T> ApiResponse<T> of(T data) {
            return new ApiResponse<>(data, null, Instant.now());
        }
        public static <T> ApiResponse<T> of(T data, String message) {
            return new ApiResponse<>(data, message, Instant.now());
        }
    }

    public record PageResponse<T>(java.util.List<T> content, int page, int size, long totalElements, int totalPages) {}

    public static <T> ResponseEntity<ApiResponse<T>> okApi(T data) {
        return ResponseEntity.ok(ApiResponse.of(data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> createdApi(T data, String message) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.of(data, message));
    }
}
