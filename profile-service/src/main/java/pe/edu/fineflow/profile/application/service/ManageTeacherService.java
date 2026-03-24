package pe.edu.fineflow.profile.application.service;
import org.springframework.stereotype.Service;
import pe.edu.fineflow.common.exception.BusinessException;
import pe.edu.fineflow.common.exception.ResourceNotFoundException;
import pe.edu.fineflow.common.tenant.TenantContext;
import pe.edu.fineflow.common.util.UuidGenerator;
import pe.edu.fineflow.profile.application.port.in.ManageTeacherUseCase;
import pe.edu.fineflow.profile.domain.model.Teacher;
import pe.edu.fineflow.profile.domain.port.out.TeacherRepositoryPort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Instant;

@Service
public class ManageTeacherService implements ManageTeacherUseCase {
    private final TeacherRepositoryPort repo;
    public ManageTeacherService(TeacherRepositoryPort repo) { this.repo = repo; }

    @Override
    public Mono<Teacher> create(Teacher t) {
        return TenantContext.getSchoolId().flatMap(sid ->
            repo.existsByDocumentNumberAndSchoolId(t.getDocumentNumber(), sid)
                .flatMap(ex -> { if (ex) return Mono.error(BusinessException.conflict("TEACHER_DUPLICATE","Ya existe un docente con ese DNI."));
                    t.setId(UuidGenerator.generate()); t.setSchoolId(sid); t.setStatus("ACTIVE"); t.setCreatedAt(Instant.now());
                    return repo.save(t); })
        );
    }
    @Override
    public Mono<Teacher> update(String id, Teacher u) {
        return TenantContext.getSchoolId().flatMap(sid ->
            repo.findByIdAndSchoolId(id, sid)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Teacher", id)))
                .flatMap(e -> { e.setFirstName(u.getFirstName()); e.setLastName(u.getLastName()); e.setSpecialty(u.getSpecialty()); return repo.save(e); })
        );
    }
    @Override
    public Mono<Void>    delete(String id)  { return TenantContext.getSchoolId().flatMap(sid -> repo.deleteByIdAndSchoolId(id, sid)); }
    @Override
    public Mono<Teacher> findById(String id){ return TenantContext.getSchoolId().flatMap(sid -> repo.findByIdAndSchoolId(id, sid).switchIfEmpty(Mono.error(new ResourceNotFoundException("Teacher", id)))); }
    @Override
    public Flux<Teacher> findAll()          { return TenantContext.getSchoolId().flatMapMany(repo::findAllBySchoolId); }
}
