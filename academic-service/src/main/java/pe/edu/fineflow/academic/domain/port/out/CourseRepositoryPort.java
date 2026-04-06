package pe.edu.fineflow.academic.domain.port.out;

import pe.edu.fineflow.academic.domain.model.Course;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseRepositoryPort {
    Mono<Course> save(Course course);
    Mono<Course> findById(String id);
    Flux<Course> findAllBySchoolId(String schoolId);
    Mono<Void> deleteById(String id);
}
