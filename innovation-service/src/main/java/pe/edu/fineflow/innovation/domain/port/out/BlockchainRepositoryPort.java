package pe.edu.fineflow.innovation.domain.port.out;
import pe.edu.fineflow.innovation.domain.model.BlockchainBlock;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface BlockchainRepositoryPort {
    Mono<BlockchainBlock> save(BlockchainBlock block);
    Mono<BlockchainBlock> findLatestBySchoolId(String schoolId);
    Mono<Integer>         countBySchoolId(String schoolId);
    Flux<BlockchainBlock> findAllBySchoolId(String schoolId);
}
