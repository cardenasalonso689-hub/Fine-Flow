package pe.edu.fineflow.report.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.report.infrastructure.adapter.out.persistence.entity.ReportJobEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReportJobR2dbcRepository extends R2dbcRepository<ReportJobEntity, String> {
    Mono<ReportJobEntity> findByIdAndSchoolId(String id, String schoolId);
    Flux<ReportJobEntity> findByRequestedByAndSchoolId(String userId, String schoolId);
    Flux<ReportJobEntity> findByStatus(String status);
}
