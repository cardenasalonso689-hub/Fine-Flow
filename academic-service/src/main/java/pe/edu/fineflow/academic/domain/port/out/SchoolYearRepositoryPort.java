package pe.edu.fineflow.academic.domain.port.out;

import pe.edu.fineflow.academic.domain.model.SchoolYear;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SchoolYearRepositoryPort {
    Mono<SchoolYear> save(SchoolYear year);
    Mono<SchoolYear> findById(String id);
    Flux<SchoolYear> findAllBySchoolId(String schoolId);
    Flux<SchoolYear> findAllByAcademicLevelId(String academicLevelId);
    Mono<Void> deleteById(String id);
}
