package pe.edu.fineflow.academic.application.port.in;

import pe.edu.fineflow.academic.domain.model.ClassTask;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageClassTaskUseCase {
    Mono<ClassTask> create(ClassTask task);
    Mono<ClassTask> update(String id, ClassTask task);
    Mono<Void> delete(String id);
    Mono<ClassTask> findById(String id);
    Flux<ClassTask> findAll();
    Flux<ClassTask> findByCourseAssignment(String courseAssignmentId);
}
