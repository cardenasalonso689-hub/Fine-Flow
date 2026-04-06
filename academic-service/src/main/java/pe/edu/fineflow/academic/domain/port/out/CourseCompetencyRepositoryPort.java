package pe.edu.fineflow.academic.domain.port.out;

import pe.edu.fineflow.academic.domain.model.CourseCompetency;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseCompetencyRepositoryPort {
    Mono<CourseCompetency> save(CourseCompetency competency);
    Mono<CourseCompetency> findById(String id);
    Flux<CourseCompetency> findAllBySchoolId(String schoolId);
    Flux<CourseCompetency> findAllByCourseId(String courseId);
    Mono<Void> deleteById(String id);
}
