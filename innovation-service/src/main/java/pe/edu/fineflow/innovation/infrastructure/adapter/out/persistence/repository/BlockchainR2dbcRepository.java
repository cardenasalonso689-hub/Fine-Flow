package pe.edu.fineflow.innovation.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.innovation.infrastructure.adapter.out.persistence.entity.BlockchainBlockEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BlockchainR2dbcRepository extends R2dbcRepository<BlockchainBlockEntity, String> {
    Mono<BlockchainBlockEntity> findFirstBySchoolIdOrderByBlockIndexDesc(String schoolId);
    Flux<BlockchainBlockEntity> findAllBySchoolIdOrderByBlockIndexDesc(String schoolId);
    Mono<Long> countBySchoolId(String schoolId);
}
