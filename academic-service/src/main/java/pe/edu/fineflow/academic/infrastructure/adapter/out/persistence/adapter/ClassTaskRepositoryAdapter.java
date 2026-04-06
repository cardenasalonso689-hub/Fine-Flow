package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.fineflow.academic.domain.model.ClassTask;
import pe.edu.fineflow.academic.domain.port.out.ClassTaskRepositoryPort;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.mapper.ClassTaskMapper;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.repository.ClassTaskR2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClassTaskRepositoryAdapter implements ClassTaskRepositoryPort {
    private final ClassTaskR2dbcRepository repository;
    private final ClassTaskMapper mapper;

    @Override
    public Mono<ClassTask> save(ClassTask task) {
        return repository.save(mapper.toEntity(task)).map(mapper::toDomain);
    }

    @Override
    public Mono<ClassTask> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Flux<ClassTask> findAllBySchoolId(String schoolId) {
        return repository.findAllBySchoolId(schoolId).map(mapper::toDomain);
    }

    @Override
    public Flux<ClassTask> findAllByCourseAssignmentId(String courseAssignmentId) {
        return repository.findAllByCourseAssignmentId(courseAssignmentId).map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
