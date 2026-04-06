package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.fineflow.academic.domain.model.Course;
import pe.edu.fineflow.academic.domain.port.out.CourseRepositoryPort;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.mapper.CourseMapper;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.repository.CourseR2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CourseRepositoryAdapter implements CourseRepositoryPort {
    private final CourseR2dbcRepository repository;
    private final CourseMapper mapper;

    @Override
    public Mono<Course> save(Course course) {
        return repository.save(mapper.toEntity(course)).map(mapper::toDomain);
    }

    @Override
    public Mono<Course> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Flux<Course> findAllBySchoolId(String schoolId) {
        return repository.findAllBySchoolId(schoolId).map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
