package pe.edu.fineflow.academic.application.port.in;

import pe.edu.fineflow.academic.domain.model.Section;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageSectionUseCase {
    Mono<Section> create(Section section);
    Mono<Section> update(String id, Section section);
    Mono<Void> delete(String id);
    Mono<Section> findById(String id);
    Flux<Section> findAll();
    Flux<Section> findBySchoolYear(String schoolYearId);
}
