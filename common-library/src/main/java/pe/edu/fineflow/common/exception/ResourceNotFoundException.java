package pe.edu.fineflow.common.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String resource, String id) {
        super("NOT_FOUND",
              "Recurso '%s' con identificador '%s' no encontrado.".formatted(resource, id),
              HttpStatus.NOT_FOUND);
    }
}
