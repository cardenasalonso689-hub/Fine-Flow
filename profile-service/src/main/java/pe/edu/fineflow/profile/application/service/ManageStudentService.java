package pe.edu.fineflow.profile.application.service;
import org.springframework.stereotype.Service;
import pe.edu.fineflow.common.event.EventBus;
import pe.edu.fineflow.common.event.StudentEnrolledEvent;
import pe.edu.fineflow.common.exception.BusinessException;
import pe.edu.fineflow.common.exception.ResourceNotFoundException;
import pe.edu.fineflow.common.tenant.TenantContext;
import pe.edu.fineflow.common.util.UuidGenerator;
import pe.edu.fineflow.profile.application.port.in.ManageStudentUseCase;
import pe.edu.fineflow.profile.domain.model.Student;
import pe.edu.fineflow.profile.domain.port.out.StudentRepositoryPort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Instant;

@Service
public class ManageStudentService implements ManageStudentUseCase {

    private final StudentRepositoryPort repo;
    private final EventBus eventBus;

    public ManageStudentService(StudentRepositoryPort repo, EventBus eventBus) {
        this.repo = repo; this.eventBus = eventBus;
    }

    @Override
    public Mono<Student> create(Student student) {
        return TenantContext.getSchoolId().flatMap(schoolId ->
            repo.existsByDocumentNumberAndSchoolId(student.getDocumentNumber(), schoolId)
                .flatMap(exists -> {
                    if (exists) return Mono.error(BusinessException.conflict(
                            "STUDENT_DUPLICATE", "Ya existe un alumno con ese DNI."));
                    student.setId(UuidGenerator.generate());
                    student.setSchoolId(schoolId);
                    student.setStatus("ACTIVE");
                    student.setCreatedAt(Instant.now());
                    return repo.save(student);
                })
                .doOnSuccess(s -> {
                    if (s.getSectionId() != null)
                        eventBus.publish(new StudentEnrolledEvent(schoolId, "system", s.getId(), s.getSectionId()));
                })
        );
    }

    @Override
    public Mono<Student> update(String id, Student updated) {
        return TenantContext.getSchoolId().flatMap(schoolId ->
            repo.findByIdAndSchoolId(id, schoolId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Student", id)))
                .flatMap(e -> { e.setFirstName(updated.getFirstName()); e.setLastName(updated.getLastName()); e.setSectionId(updated.getSectionId()); return repo.save(e); })
        );
    }

    @Override
    public Mono<Void>    delete(String id)          { return TenantContext.getSchoolId().flatMap(sid -> repo.deleteByIdAndSchoolId(id, sid)); }
    @Override
    public Mono<Student> findById(String id)        { return TenantContext.getSchoolId().flatMap(sid -> repo.findByIdAndSchoolId(id, sid).switchIfEmpty(Mono.error(new ResourceNotFoundException("Student", id)))); }
    @Override
    public Flux<Student> findAllBySection(String s) { return TenantContext.getSchoolId().flatMapMany(sid -> repo.findAllBySectionIdAndSchoolId(s, sid)); }
    @Override
    public Flux<Student> search(String q)           { return TenantContext.getSchoolId().flatMapMany(repo::findAllBySchoolId).filter(s -> (s.getFirstName()+" "+s.getLastName()).toLowerCase().contains(q.toLowerCase()) || s.getDocumentNumber().contains(q)); }
}
