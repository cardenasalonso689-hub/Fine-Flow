package pe.edu.fineflow.academic.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.fineflow.academic.application.port.in.ManageCourseCompetencyUseCase;
import pe.edu.fineflow.academic.domain.model.CourseCompetency;
import pe.edu.fineflow.academic.domain.port.out.CourseCompetencyRepositoryPort;
import pe.edu.fineflow.common.tenant.TenantContext;
import pe.edu.fineflow.common.util.UuidGenerator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ManageCourseCompetencyService implements ManageCourseCompetencyUseCase {
    private final CourseCompetencyRepositoryPort repository;

    @Override
    public Mono<CourseCompetency> create(CourseCompetency competency) {
        return TenantContext.getSchoolId().flatMap(schoolId -> {
            competency.setId(UuidGenerator.generate());
            competency.setSchoolId(schoolId);
            competency.setIsActive(1);
            competency.setCreatedAt(Instant.now());
            return repository.save(competency);
        });
    }

    @Override
    public Mono<CourseCompetency> update(String id, CourseCompetency updated) {
        return repository.findById(id)
            .flatMap(existing -> {
                existing.setName(updated.getName());
                existing.setDescription(updated.getDescription());
                existing.setWeight(updated.getWeight());
                existing.setIsActive(updated.getIsActive());
                return repository.save(existing);
            });
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<CourseCompetency> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<CourseCompetency> findAll() {
        return TenantContext.getSchoolId().flatMapMany(repository::findAllBySchoolId);
    }

    @Override
    public Flux<CourseCompetency> findByCourse(String courseId) {
        return repository.findAllByCourseId(courseId);
    }
}
