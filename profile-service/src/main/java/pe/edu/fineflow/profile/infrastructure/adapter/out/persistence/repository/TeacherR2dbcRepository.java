package pe.edu.fineflow.profile.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.profile.infrastructure.adapter.out.persistence.entity.TeacherEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TeacherR2dbcRepository extends R2dbcRepository<TeacherEntity, String> {
    Mono<TeacherEntity> findByIdAndSchoolId(String id, String schoolId);
    Flux<TeacherEntity> findAllBySchoolId(String schoolId);
    Mono<Boolean> existsByDocumentNumberAndSchoolId(String documentNumber, String schoolId);
    Mono<Void> deleteByIdAndSchoolId(String id, String schoolId);
}
