package pe.edu.fineflow.academic.domain.port.out;

import pe.edu.fineflow.academic.domain.model.CourseAssignment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseAssignmentRepositoryPort {
    Mono<CourseAssignment> save(CourseAssignment assignment);
    Mono<CourseAssignment> findById(String id);
    Flux<CourseAssignment> findAllBySchoolId(String schoolId);
    Flux<CourseAssignment> findAllBySectionId(String sectionId);
    Mono<Void> deleteById(String id);
}
