package pe.edu.fineflow.identity.application.port.in;

import pe.edu.fineflow.identity.infrastructure.adapter.in.web.dto.AuthRequest;
import pe.edu.fineflow.identity.infrastructure.adapter.in.web.dto.AuthResponse;
import pe.edu.fineflow.identity.infrastructure.adapter.in.web.dto.RegisterRequest;
import reactor.core.publisher.Mono;

public interface AuthUseCase {
    Mono<AuthResponse> login(AuthRequest request);
    Mono<AuthResponse> register(RegisterRequest request);
}
