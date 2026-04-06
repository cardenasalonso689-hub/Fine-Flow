package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.entity.SectionEntity;
import reactor.core.publisher.Flux;

@Repository
public interface SectionR2dbcRepository extends ReactiveCrudRepository<SectionEntity, String> {
    Flux<SectionEntity> findAllBySchoolId(String schoolId);
    Flux<SectionEntity> findAllBySchoolYearId(String schoolYearId);
}
