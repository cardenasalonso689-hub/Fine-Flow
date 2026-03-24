package pe.edu.fineflow.gateway.filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pe.edu.fineflow.common.security.JwtProvider;
import reactor.core.publisher.Mono;
import java.util.List;

/**
 * Filtro global del Gateway. Valida el JWT ANTES de enrutar.
 * Las rutas en PUBLIC_PATHS pasan sin validación.
 */
@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private static final String BEARER = "Bearer ";
    private static final List<String> PUBLIC_PATHS = List.of(
            "/api/auth/", "/actuator/", "/v3/api-docs/", "/swagger-ui/"
    );

    private final JwtProvider jwtProvider;
    public JwtAuthenticationFilter(JwtProvider jwtProvider) { this.jwtProvider = jwtProvider; }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        if (PUBLIC_PATHS.stream().anyMatch(path::startsWith)) return chain.filter(exchange);

        String auth = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (auth == null || !auth.startsWith(BEARER)) {
            log.warn("Gateway: no token para [{}]", path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = auth.substring(BEARER.length());
        if (!jwtProvider.isValid(token)) {
            log.warn("Gateway: token inválido para [{}]", path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    @Override public int getOrder() { return -100; } // Ejecutar antes de todo
}
