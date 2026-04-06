package pe.edu.fineflow.academic.domain.port.out;

import pe.edu.fineflow.academic.domain.model.AcademicPeriod;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AcademicPeriodRepositoryPort {
    Mono<AcademicPeriod> save(AcademicPeriod period);
    Mono<AcademicPeriod> findById(String id);
    Flux<AcademicPeriod> findAllBySchoolId(String schoolId);
    Flux<AcademicPeriod> findAllBySchoolYearId(String schoolYearId);
    Mono<Void> deleteById(String id);
}
