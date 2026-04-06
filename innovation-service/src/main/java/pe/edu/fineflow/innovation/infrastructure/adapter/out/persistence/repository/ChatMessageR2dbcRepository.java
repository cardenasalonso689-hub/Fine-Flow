package pe.edu.fineflow.innovation.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.innovation.infrastructure.adapter.out.persistence.entity.ChatMessageEntity;
import reactor.core.publisher.Flux;

@Repository
public interface ChatMessageR2dbcRepository extends R2dbcRepository<ChatMessageEntity, String> {
    Flux<ChatMessageEntity> findBySessionIdOrderByCreatedAtAsc(String sessionId);
}
