package pe.edu.fineflow.academic.domain.port.out;

import pe.edu.fineflow.academic.domain.model.ClassTask;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClassTaskRepositoryPort {
    Mono<ClassTask> save(ClassTask task);
    Mono<ClassTask> findById(String id);
    Flux<ClassTask> findAllBySchoolId(String schoolId);
    Flux<ClassTask> findAllByCourseAssignmentId(String courseAssignmentId);
    Mono<Void> deleteById(String id);
}
