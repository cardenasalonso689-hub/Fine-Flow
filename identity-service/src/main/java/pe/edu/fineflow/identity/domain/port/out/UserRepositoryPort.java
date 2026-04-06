package pe.edu.fineflow.identity.domain.port.out;

import pe.edu.fineflow.identity.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepositoryPort {
    Mono<User> save(User user);
    Mono<User> findById(String id);
    Mono<User> findByEmailAndSchoolId(String email, String schoolId);
    Mono<User> findByEmail(String email);
    Mono<Boolean> existsByEmailAndSchoolId(String email, String schoolId);
    Flux<User> findAllBySchoolId(String schoolId);
    Mono<Void> deleteById(String id);
}
