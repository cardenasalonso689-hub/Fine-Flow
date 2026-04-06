package pe.edu.fineflow.innovation.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.innovation.infrastructure.adapter.out.persistence.entity.ChatSessionEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ChatSessionR2dbcRepository extends R2dbcRepository<ChatSessionEntity, String> {
    Mono<ChatSessionEntity> findByIdAndSchoolId(String id, String schoolId);
}
