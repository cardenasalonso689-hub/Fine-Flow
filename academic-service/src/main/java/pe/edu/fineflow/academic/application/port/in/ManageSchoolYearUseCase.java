package pe.edu.fineflow.academic.application.port.in;

import pe.edu.fineflow.academic.domain.model.SchoolYear;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageSchoolYearUseCase {
    Mono<SchoolYear> create(SchoolYear year);
    Mono<SchoolYear> update(String id, SchoolYear year);
    Mono<Void> delete(String id);
    Mono<SchoolYear> findById(String id);
    Flux<SchoolYear> findAll();
    Flux<SchoolYear> findByAcademicLevel(String academicLevelId);
}
