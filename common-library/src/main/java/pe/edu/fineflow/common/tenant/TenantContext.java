package pe.edu.fineflow.common.tenant;

import pe.edu.fineflow.common.security.UserPrincipal;
import reactor.core.publisher.Mono;

/**
 * Acceso al contexto del tenant (colegio) desde cualquier capa reactiva.
 *
 * USAGE:
 *   TenantContext.getSchoolId()
 *       .flatMap(schoolId -> repo.findAllBySchoolId(schoolId))
 */
public final class TenantContext {

    public static final String PRINCIPAL_KEY = "FINEFLOW_PRINCIPAL";
    public static final String SCHOOL_ID_KEY = "FINEFLOW_SCHOOL_ID";

    private TenantContext() {}

    /** Obtiene el school_id del Reactor Context. Emite error si no está presente. */
    public static Mono<String> getSchoolId() {
        return Mono.deferContextual(ctx -> {
            if (!ctx.hasKey(SCHOOL_ID_KEY)) {
                return Mono.error(new IllegalStateException(
                        "TenantContext: school_id no encontrado en el Reactor Context. " +
                        "¿Se ejecutó el TenantWebFilter?"));
            }
            return Mono.just(ctx.<String>get(SCHOOL_ID_KEY));
        });
    }

    /** Obtiene el UserPrincipal completo del Reactor Context. */
    public static Mono<UserPrincipal> getPrincipal() {
        return Mono.deferContextual(ctx -> {
            if (!ctx.hasKey(PRINCIPAL_KEY)) {
                return Mono.error(new IllegalStateException(
                        "TenantContext: UserPrincipal no encontrado en el Reactor Context."));
            }
            return Mono.just(ctx.<UserPrincipal>get(PRINCIPAL_KEY));
        });
    }
}
