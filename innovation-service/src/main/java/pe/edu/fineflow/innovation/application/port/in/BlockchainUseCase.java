package pe.edu.fineflow.innovation.application.port.in;
import pe.edu.fineflow.innovation.domain.model.BlockchainBlock;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface BlockchainUseCase {
    Mono<BlockchainBlock> appendBlock(String schoolId, String triggeredBy, String eventType,
                                      String entityId, String entityType, String payloadJson);
    Mono<Boolean>         verifyChain(String schoolId);
    Flux<BlockchainBlock> getChain(String schoolId);
}
