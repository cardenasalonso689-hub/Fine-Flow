package pe.edu.fineflow.common.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.WebFilter;

/**
 * Configuración de seguridad reactiva base.
 * Cada microservicio puede extender esta clase o reusarla como está.
 */
@EnableReactiveMethodSecurity
public abstract class ReactiveSecurityConfig {

    protected final JwtProvider jwtProvider;

    protected ReactiveSecurityConfig(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    protected SecurityWebFilterChain buildChain(ServerHttpSecurity http,
                                                  WebFilter tenantFilter,
                                                  String... publicPaths) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .authorizeExchange(ex -> ex
                        .pathMatchers(publicPaths).permitAll()
                        .pathMatchers(HttpMethod.OPTIONS).permitAll()
                        .anyExchange().authenticated()
                )
                .addFilterAt(tenantFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }
}
