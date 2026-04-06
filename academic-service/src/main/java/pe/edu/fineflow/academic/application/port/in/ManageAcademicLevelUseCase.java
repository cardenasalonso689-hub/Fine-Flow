package pe.edu.fineflow.academic.application.port.in;

import pe.edu.fineflow.academic.domain.model.AcademicLevel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageAcademicLevelUseCase {
    Mono<AcademicLevel> create(AcademicLevel level);
    Mono<AcademicLevel> update(String id, AcademicLevel level);
    Mono<Void> delete(String id);
    Mono<AcademicLevel> findById(String id);
    Flux<AcademicLevel> findAll();
}
