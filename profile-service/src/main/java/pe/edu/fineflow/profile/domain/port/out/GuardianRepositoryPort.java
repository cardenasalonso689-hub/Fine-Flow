package pe.edu.fineflow.profile.domain.port.out;

import pe.edu.fineflow.profile.domain.model.Guardian;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GuardianRepositoryPort {
    Mono<Guardian> save(Guardian guardian);
    Mono<Guardian> findById(String id);
    Flux<Guardian> findAllBySchoolId(String schoolId);
    Flux<Guardian> findAllByStudentId(String studentId);
    Mono<Void> deleteById(String id);
}
