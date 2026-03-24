package pe.edu.fineflow.innovation.infrastructure.adapter.in.web;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.fineflow.common.tenant.TenantContext;
import pe.edu.fineflow.innovation.application.port.in.BlockchainUseCase;
import pe.edu.fineflow.innovation.domain.model.BlockchainBlock;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/innovation/blockchain")
@Tag(name = "Blockchain", description = "Registro inmutable de eventos académicos")
public class BlockchainController {

    private final BlockchainUseCase useCase;
    public BlockchainController(BlockchainUseCase useCase) { this.useCase = useCase; }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    public Flux<BlockchainBlock> getChain() {
        return TenantContext.getSchoolId().flatMapMany(useCase::getChain);
    }

    @GetMapping("/verify")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    public Mono<Boolean> verify() {
        return TenantContext.getSchoolId().flatMap(useCase::verifyChain);
    }
}
