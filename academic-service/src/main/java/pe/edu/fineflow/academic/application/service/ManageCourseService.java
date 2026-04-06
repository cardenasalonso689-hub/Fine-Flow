package pe.edu.fineflow.academic.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.fineflow.academic.application.port.in.ManageCourseUseCase;
import pe.edu.fineflow.academic.domain.model.Course;
import pe.edu.fineflow.academic.domain.port.out.CourseRepositoryPort;
import pe.edu.fineflow.common.tenant.TenantContext;
import pe.edu.fineflow.common.util.UuidGenerator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ManageCourseService implements ManageCourseUseCase {
    private final CourseRepositoryPort repository;

    @Override
    public Mono<Course> create(Course course) {
        return TenantContext.getSchoolId().flatMap(schoolId -> {
            course.setId(UuidGenerator.generate());
            course.setSchoolId(schoolId);
            course.setIsActive(1);
            course.setCreatedAt(Instant.now());
            return repository.save(course);
        });
    }

    @Override
    public Mono<Course> update(String id, Course updated) {
        return repository.findById(id)
            .flatMap(existing -> {
                existing.setName(updated.getName());
                existing.setCode(updated.getCode());
                existing.setDescription(updated.getDescription());
                existing.setColorHex(updated.getColorHex());
                existing.setIsActive(updated.getIsActive());
                return repository.save(existing);
            });
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Course> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Course> findAll() {
        return TenantContext.getSchoolId().flatMapMany(repository::findAllBySchoolId);
    }
}
