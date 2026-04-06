package pe.edu.fineflow.identity.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.fineflow.common.exception.AuthException;
import pe.edu.fineflow.common.security.JwtProvider;
import pe.edu.fineflow.common.security.UserPrincipal;
import pe.edu.fineflow.common.util.UuidGenerator;
import pe.edu.fineflow.identity.application.port.in.AuthUseCase;
import pe.edu.fineflow.identity.domain.model.User;
import pe.edu.fineflow.identity.domain.port.out.UserRepositoryPort;
import pe.edu.fineflow.identity.infrastructure.adapter.in.web.dto.AuthRequest;
import pe.edu.fineflow.identity.infrastructure.adapter.in.web.dto.AuthResponse;
import pe.edu.fineflow.identity.infrastructure.adapter.in.web.dto.RegisterRequest;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public Mono<AuthResponse> login(AuthRequest request) {
        return userRepository.findByEmailAndSchoolId(request.getEmail(), request.getSchoolId())
                .switchIfEmpty(Mono.error(AuthException.invalidCredentials()))
                .flatMap(user -> {
                    if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
                        return Mono.error(AuthException.invalidCredentials());
                    }
                    if (!"ACTIVE".equals(user.getStatus())) {
                        return Mono.error(AuthException.locked("Cuenta bloqueada o inactiva"));
                    }
                    user.setLastLoginAt(Instant.now());
                    return userRepository.save(user);
                })
                .map(user -> {
                    UserPrincipal principal = new UserPrincipal(
                            user.getId(), user.getSchoolId(), user.getEmail(), user.getRole(), null);
                    String accessToken = jwtProvider.generateAccessToken(principal);
                    String refreshToken = jwtProvider.generateRefreshToken(user.getId(), user.getId());
                    return new AuthResponse(accessToken, refreshToken, "Bearer", jwtProvider.getRefreshTokenMs() / 1000,
                            new AuthResponse.UserInfo(user.getId(), user.getEmail(), user.getRole(),
                                    user.getFirstName(), user.getLastName()));
                });
    }

    @Override
    public Mono<AuthResponse> register(RegisterRequest request) {
        return userRepository.existsByEmailAndSchoolId(request.getEmail(), request.getSchoolId())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(AuthException.conflict("EMAIL_ALREADY_EXISTS", "El correo ya está registrado en este colegio"));
                    }
                    User user = new User();
                    user.setId(UuidGenerator.generate());
                    user.setSchoolId(request.getSchoolId());
                    user.setEmail(request.getEmail());
                    user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
                    user.setRole(request.getRole());
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    user.setStatus("ACTIVE");
                    user.setCreatedAt(Instant.now());
                    return userRepository.save(user);
                })
                .map(user -> {
                    UserPrincipal principal = new UserPrincipal(
                            user.getId(), user.getSchoolId(), user.getEmail(), user.getRole(), null);
                    String accessToken = jwtProvider.generateAccessToken(principal);
                    String refreshToken = jwtProvider.generateRefreshToken(user.getId(), user.getId());
                    return new AuthResponse(accessToken, refreshToken, "Bearer", jwtProvider.getRefreshTokenMs() / 1000,
                            new AuthResponse.UserInfo(user.getId(), user.getEmail(), user.getRole(),
                                    user.getFirstName(), user.getLastName()));
                });
    }
}
