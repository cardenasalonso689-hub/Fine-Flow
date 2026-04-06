package pe.edu.fineflow.academic.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.fineflow.academic.application.port.in.ManageClassTaskUseCase;
import pe.edu.fineflow.academic.domain.model.ClassTask;
import pe.edu.fineflow.academic.domain.port.out.ClassTaskRepositoryPort;
import pe.edu.fineflow.common.tenant.TenantContext;
import pe.edu.fineflow.common.util.UuidGenerator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ManageClassTaskService implements ManageClassTaskUseCase {
    private final ClassTaskRepositoryPort repository;

    @Override
    public Mono<ClassTask> create(ClassTask task) {
        return TenantContext.getSchoolId().flatMap(schoolId -> {
            task.setId(UuidGenerator.generate());
            task.setSchoolId(schoolId);
            task.setIsActive(1);
            task.setCreatedAt(Instant.now());
            return repository.save(task);
        });
    }

    @Override
    public Mono<ClassTask> update(String id, ClassTask updated) {
        return repository.findById(id)
            .flatMap(existing -> {
                existing.setTitle(updated.getTitle());
                existing.setDescription(updated.getDescription());
                existing.setTaskType(updated.getTaskType());
                existing.setMaxScore(updated.getMaxScore());
                existing.setDueDate(updated.getDueDate());
                existing.setIsActive(updated.getIsActive());
                return repository.save(existing);
            });
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<ClassTask> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<ClassTask> findAll() {
        return TenantContext.getSchoolId().flatMapMany(repository::findAllBySchoolId);
    }

    @Override
    public Flux<ClassTask> findByCourseAssignment(String courseAssignmentId) {
        return repository.findAllByCourseAssignmentId(courseAssignmentId);
    }
}
