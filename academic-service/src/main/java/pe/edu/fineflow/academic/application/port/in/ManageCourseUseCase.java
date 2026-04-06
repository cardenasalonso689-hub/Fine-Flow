package pe.edu.fineflow.academic.application.port.in;

import pe.edu.fineflow.academic.domain.model.Course;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageCourseUseCase {
    Mono<Course> create(Course course);
    Mono<Course> update(String id, Course course);
    Mono<Void> delete(String id);
    Mono<Course> findById(String id);
    Flux<Course> findAll();
}
