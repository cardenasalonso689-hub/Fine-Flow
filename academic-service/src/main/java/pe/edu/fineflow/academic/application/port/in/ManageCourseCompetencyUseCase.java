package pe.edu.fineflow.academic.application.port.in;

import pe.edu.fineflow.academic.domain.model.CourseCompetency;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageCourseCompetencyUseCase {
    Mono<CourseCompetency> create(CourseCompetency competency);
    Mono<CourseCompetency> update(String id, CourseCompetency competency);
    Mono<Void> delete(String id);
    Mono<CourseCompetency> findById(String id);
    Flux<CourseCompetency> findAll();
    Flux<CourseCompetency> findByCourse(String courseId);
}
