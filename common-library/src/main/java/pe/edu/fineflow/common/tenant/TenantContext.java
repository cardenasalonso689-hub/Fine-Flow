package pe.edu.fineflow.common.tenant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pe.edu.fineflow.common.security.UserPrincipal;
import reactor.core.publisher.Mono;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TenantContext {

    public static final String PRINCIPAL_KEY = "FINEFLOW_PRINCIPAL";
    public static final String SCHOOL_ID_KEY = "FINEFLOW_SCHOOL_ID";

    public static Mono<String> getSchoolId() {
        return Mono.deferContextual(ctx -> ctx.hasKey(SCHOOL_ID_KEY)
                ? Mono.just(ctx.<String>get(SCHOOL_ID_KEY))
                : Mono.error(new IllegalStateException("school_id no encontrado en TenantContext")));
    }

    public static Mono<UserPrincipal> getPrincipal() {
        return Mono.deferContextual(ctx -> ctx.hasKey(PRINCIPAL_KEY)
                ? Mono.just(ctx.<UserPrincipal>get(PRINCIPAL_KEY))
                : Mono.error(new IllegalStateException("UserPrincipal no encontrado en TenantContext")));
    }
}
