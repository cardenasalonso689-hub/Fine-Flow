package pe.edu.fineflow.evaluation.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.evaluation.infrastructure.adapter.out.persistence.entity.StudentScoreEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StudentScoreR2dbcRepository extends R2dbcRepository<StudentScoreEntity, String> {
    Mono<StudentScoreEntity> findByIdAndSchoolId(String id, String schoolId);
    Flux<StudentScoreEntity> findByStudentIdAndSchoolId(String studentId, String schoolId);
    Flux<StudentScoreEntity> findByClassTaskIdAndSchoolId(String classTaskId, String schoolId);
    Mono<Boolean> existsByStudentIdAndClassTaskId(String studentId, String classTaskId);
    Mono<Void> deleteByIdAndSchoolId(String id, String schoolId);
}
