package pe.edu.fineflow.common.tenant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import pe.edu.fineflow.common.security.JwtProvider;
import pe.edu.fineflow.common.security.UserPrincipal;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TenantWebFilter implements WebFilter {

    private static final String BEARER_PREFIX = "Bearer ";
    private static final List<String> PUBLIC_PATHS = List.of(
            "/actuator", "/v3/api-docs", "/swagger-ui", "/webjars");
    private final JwtProvider jwtProvider;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        if (isPublicPath(path))
            return chain.filter(exchange);

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            return unauthorized(exchange, "No Bearer token");
        }

        String token = authHeader.substring(BEARER_PREFIX.length());
        if (!jwtProvider.isValid(token)) {
            return unauthorized(exchange, "Token inválido o expirado");
        }

        UserPrincipal principal = jwtProvider.extractPrincipal(token);
        log.debug("Auth: user={} school={} path={}", principal.userId(), principal.schoolId(), path);

        var authentication = new UsernamePasswordAuthenticationToken(
                principal, null,
                principal.authorities().stream().map(SimpleGrantedAuthority::new).toList());

        return chain.filter(exchange)
                .contextWrite(ctx -> ctx
                        .put(TenantContext.PRINCIPAL_KEY, principal)
                        .put(TenantContext.SCHOOL_ID_KEY, principal.schoolId()))
                .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange, String reason) {
        log.warn("Auth failed: {} - {}", exchange.getRequest().getPath().value(), reason);
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    private boolean isPublicPath(String path) {
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }
}
