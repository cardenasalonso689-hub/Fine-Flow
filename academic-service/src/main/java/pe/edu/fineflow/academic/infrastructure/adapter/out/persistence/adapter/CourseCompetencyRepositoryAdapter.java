package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.fineflow.academic.domain.model.CourseCompetency;
import pe.edu.fineflow.academic.domain.port.out.CourseCompetencyRepositoryPort;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.mapper.CourseCompetencyMapper;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.repository.CourseCompetencyR2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CourseCompetencyRepositoryAdapter implements CourseCompetencyRepositoryPort {
    private final CourseCompetencyR2dbcRepository repository;
    private final CourseCompetencyMapper mapper;

    @Override
    public Mono<CourseCompetency> save(CourseCompetency competency) {
        return repository.save(mapper.toEntity(competency)).map(mapper::toDomain);
    }

    @Override
    public Mono<CourseCompetency> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Flux<CourseCompetency> findAllBySchoolId(String schoolId) {
        return repository.findAllBySchoolId(schoolId).map(mapper::toDomain);
    }

    @Override
    public Flux<CourseCompetency> findAllByCourseId(String courseId) {
        return repository.findAllByCourseId(courseId).map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
