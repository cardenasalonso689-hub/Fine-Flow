package pe.edu.fineflow.innovation.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.fineflow.innovation.domain.model.BlockchainBlock;
import pe.edu.fineflow.innovation.domain.port.out.BlockchainRepositoryPort;
import pe.edu.fineflow.innovation.infrastructure.adapter.out.persistence.entity.BlockchainBlockEntity;
import pe.edu.fineflow.innovation.infrastructure.adapter.out.persistence.repository.BlockchainR2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BlockchainRepositoryAdapter implements BlockchainRepositoryPort {

    private final BlockchainR2dbcRepository repository;

    @Override
    public Mono<BlockchainBlock> save(BlockchainBlock block) {
        return repository.save(toEntity(block)).map(this::toModel);
    }

    @Override
    public Mono<BlockchainBlock> findLatestBySchoolId(String schoolId) {
        return repository.findFirstBySchoolIdOrderByBlockIndexDesc(schoolId).map(this::toModel);
    }

    @Override
    public Mono<Integer> countBySchoolId(String schoolId) {
        return repository.countBySchoolId(schoolId).map(Long::intValue);
    }

    @Override
    public Flux<BlockchainBlock> findAllBySchoolId(String schoolId) {
        return repository.findAllBySchoolIdOrderByBlockIndexDesc(schoolId).map(this::toModel);
    }

    private BlockchainBlockEntity toEntity(BlockchainBlock m) {
        BlockchainBlockEntity e = new BlockchainBlockEntity();
        e.setId(m.getId());
        e.setSchoolId(m.getSchoolId());
        e.setEventType(m.getEventType());
        e.setEntityId(m.getEntityId());
        e.setEntityType(m.getEntityType());
        e.setPayload(m.getPayload());
        e.setPreviousHash(m.getPreviousHash());
        e.setHash(m.getHash());
        e.setCreatedBy(m.getCreatedBy());
        e.setBlockIndex(m.getBlockIndex());
        return e;
    }

    private BlockchainBlock toModel(BlockchainBlockEntity e) {
        return new BlockchainBlock(
            e.getId(), e.getSchoolId(), e.getEventType(), e.getEntityId(),
            e.getEntityType(), e.getPayload(), e.getPreviousHash(), e.getHash(),
            e.getCreatedBy(), e.getBlockIndex(), e.getCreatedAt()
        );
    }
}
