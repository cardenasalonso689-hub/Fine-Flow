package pe.edu.fineflow.common.tenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import pe.edu.fineflow.common.security.JwtProvider;
import pe.edu.fineflow.common.security.UserPrincipal;
import reactor.core.publisher.Mono;
import java.util.List;

/**
 * Filtro reactivo que:
 *  1. Extrae el Bearer Token del header Authorization
 *  2. Valida la firma JWT
 *  3. Extrae el UserPrincipal (userId, schoolId, role)
 *  4. Inyecta el principal en el Reactor Context (TenantContext)
 *  5. Configura el SecurityContext de Spring para @PreAuthorize
 *
 * Las rutas públicas (/actuator/**, /v3/api-docs/**) son ignoradas.
 */
public class TenantWebFilter implements WebFilter {

    private static final Logger log = LoggerFactory.getLogger(TenantWebFilter.class);
    private static final String BEARER_PREFIX = "Bearer ";

    private static final List<String> PUBLIC_PATHS = List.of(
            "/actuator", "/v3/api-docs", "/swagger-ui", "/webjars"
    );

    private final JwtProvider jwtProvider;

    public TenantWebFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getPath().value();

        // Rutas públicas — pasar sin validar
        if (isPublicPath(path)) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            log.debug("TenantWebFilter: no Bearer token en [{}]", path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(BEARER_PREFIX.length());

        if (!jwtProvider.isValid(token)) {
            log.warn("TenantWebFilter: token inválido o expirado en [{}]", path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        UserPrincipal principal = jwtProvider.extractPrincipal(token);
        log.debug("TenantWebFilter: principal={} school={} path={}",
                  principal.userId(), principal.schoolId(), path);

        // Construcción del Authentication de Spring Security
        var authorities = principal.authorities().stream()
                .map(SimpleGrantedAuthority::new).toList();
        var authentication = new UsernamePasswordAuthenticationToken(
                principal, null, authorities);

        return chain.filter(exchange)
                .contextWrite(ctx -> ctx
                        .put(TenantContext.PRINCIPAL_KEY, principal)
                        .put(TenantContext.SCHOOL_ID_KEY, principal.schoolId())
                )
                .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
    }

    private boolean isPublicPath(String path) {
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }
}
