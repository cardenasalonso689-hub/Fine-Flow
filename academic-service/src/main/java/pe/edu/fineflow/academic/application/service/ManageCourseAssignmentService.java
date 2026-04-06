package pe.edu.fineflow.academic.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.fineflow.academic.application.port.in.ManageCourseAssignmentUseCase;
import pe.edu.fineflow.academic.domain.model.CourseAssignment;
import pe.edu.fineflow.academic.domain.port.out.CourseAssignmentRepositoryPort;
import pe.edu.fineflow.common.tenant.TenantContext;
import pe.edu.fineflow.common.util.UuidGenerator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ManageCourseAssignmentService implements ManageCourseAssignmentUseCase {
    private final CourseAssignmentRepositoryPort repository;

    @Override
    public Mono<CourseAssignment> create(CourseAssignment assignment) {
        return TenantContext.getSchoolId().flatMap(schoolId -> {
            assignment.setId(UuidGenerator.generate());
            assignment.setSchoolId(schoolId);
            assignment.setIsActive(1);
            assignment.setCreatedAt(Instant.now());
            return repository.save(assignment);
        });
    }

    @Override
    public Mono<CourseAssignment> update(String id, CourseAssignment updated) {
        return repository.findById(id)
            .flatMap(existing -> {
                existing.setCourseId(updated.getCourseId());
                existing.setSectionId(updated.getSectionId());
                existing.setTeacherId(updated.getTeacherId());
                existing.setAcademicPeriodId(updated.getAcademicPeriodId());
                existing.setHoursPerWeek(updated.getHoursPerWeek());
                existing.setIsActive(updated.getIsActive());
                return repository.save(existing);
            });
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<CourseAssignment> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<CourseAssignment> findAll() {
        return TenantContext.getSchoolId().flatMapMany(repository::findAllBySchoolId);
    }

    @Override
    public Flux<CourseAssignment> findBySection(String sectionId) {
        return repository.findAllBySectionId(sectionId);
    }
}
