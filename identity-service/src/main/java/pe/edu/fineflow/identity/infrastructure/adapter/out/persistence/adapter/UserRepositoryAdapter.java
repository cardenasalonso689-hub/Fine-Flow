package pe.edu.fineflow.identity.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.fineflow.identity.domain.model.User;
import pe.edu.fineflow.identity.domain.port.out.UserRepositoryPort;
import pe.edu.fineflow.identity.infrastructure.adapter.out.persistence.mapper.UserPersistenceMapper;
import pe.edu.fineflow.identity.infrastructure.adapter.out.persistence.repository.UserR2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserR2dbcRepository repository;
    private final UserPersistenceMapper mapper;

    @Override
    public Mono<User> save(User user) {
        return repository.save(mapper.toEntity(user)).map(mapper::toDomain);
    }

    @Override
    public Mono<User> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Mono<User> findByEmailAndSchoolId(String email, String schoolId) {
        return repository.findByEmailAndSchoolId(email, schoolId).map(mapper::toDomain);
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public Mono<Boolean> existsByEmailAndSchoolId(String email, String schoolId) {
        return repository.existsByEmailAndSchoolId(email, schoolId);
    }

    @Override
    public Flux<User> findAllBySchoolId(String schoolId) {
        return repository.findAllBySchoolId(schoolId).map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
