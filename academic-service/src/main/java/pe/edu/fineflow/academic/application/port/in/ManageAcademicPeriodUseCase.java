package pe.edu.fineflow.academic.application.port.in;

import pe.edu.fineflow.academic.domain.model.AcademicPeriod;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageAcademicPeriodUseCase {
    Mono<AcademicPeriod> create(AcademicPeriod period);
    Mono<AcademicPeriod> update(String id, AcademicPeriod period);
    Mono<Void> delete(String id);
    Mono<AcademicPeriod> findById(String id);
    Flux<AcademicPeriod> findAll();
    Flux<AcademicPeriod> findBySchoolYear(String schoolYearId);
}
