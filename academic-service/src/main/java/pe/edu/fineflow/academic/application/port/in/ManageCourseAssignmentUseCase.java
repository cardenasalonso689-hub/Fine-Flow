package pe.edu.fineflow.academic.application.port.in;

import pe.edu.fineflow.academic.domain.model.CourseAssignment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageCourseAssignmentUseCase {
    Mono<CourseAssignment> create(CourseAssignment assignment);
    Mono<CourseAssignment> update(String id, CourseAssignment assignment);
    Mono<Void> delete(String id);
    Mono<CourseAssignment> findById(String id);
    Flux<CourseAssignment> findAll();
    Flux<CourseAssignment> findBySection(String sectionId);
}
