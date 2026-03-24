package pe.edu.fineflow.evaluation.application.port.in;

import pe.edu.fineflow.evaluation.domain.model.StudentScore;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RegisterScoreUseCase {
    Mono<StudentScore> register(StudentScore score);

    Mono<StudentScore> update(String id, StudentScore score);

    Mono<Void> delete(String id);

    Flux<StudentScore> findByStudent(String studentId);

    Flux<StudentScore> findByClassTask(String classTaskId);
}
