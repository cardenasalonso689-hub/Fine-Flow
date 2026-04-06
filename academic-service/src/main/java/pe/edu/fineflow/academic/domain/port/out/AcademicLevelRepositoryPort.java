package pe.edu.fineflow.academic.domain.port.out;

import pe.edu.fineflow.academic.domain.model.AcademicLevel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AcademicLevelRepositoryPort {
    Mono<AcademicLevel> save(AcademicLevel level);
    Mono<AcademicLevel> findById(String id);
    Flux<AcademicLevel> findAllBySchoolId(String schoolId);
    Mono<Void> deleteById(String id);
}
