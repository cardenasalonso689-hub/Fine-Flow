package pe.edu.fineflow.evaluation.application.service;
import org.springframework.stereotype.Service;
import pe.edu.fineflow.common.event.EventBus;
import pe.edu.fineflow.common.event.ScoreRegisteredEvent;
import pe.edu.fineflow.common.exception.BusinessException;
import pe.edu.fineflow.common.exception.ResourceNotFoundException;
import pe.edu.fineflow.common.tenant.TenantContext;
import pe.edu.fineflow.common.util.UuidGenerator;
import pe.edu.fineflow.evaluation.application.port.in.RegisterScoreUseCase;
import pe.edu.fineflow.evaluation.domain.model.StudentScore;
import pe.edu.fineflow.evaluation.domain.port.out.StudentScoreRepositoryPort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Instant;

@Service
public class RegisterScoreService implements RegisterScoreUseCase {

    private final StudentScoreRepositoryPort repo;
    private final EventBus eventBus;

    public RegisterScoreService(StudentScoreRepositoryPort repo, EventBus eventBus) {
        this.repo = repo; this.eventBus = eventBus;
    }

    @Override
    public Mono<StudentScore> register(StudentScore score) {
        return TenantContext.getPrincipal().flatMap(principal ->
            repo.existsByStudentIdAndClassTaskId(score.getStudentId(), score.getClassTaskId())
                .flatMap(exists -> {
                    if (exists) return Mono.error(BusinessException.conflict(
                            "SCORE_DUPLICATE", "Ya existe una nota para este alumno en esta actividad."));
                    if (score.getScore().doubleValue() < 0 || score.getScore().doubleValue() > 20)
                        return Mono.error(BusinessException.badRequest("SCORE_OUT_OF_RANGE", "La nota debe estar entre 0 y 20."));
                    score.setId(UuidGenerator.generate());
                    score.setSchoolId(principal.schoolId());
                    score.setRegisteredBy(principal.userId());
                    score.setRegisteredAt(Instant.now());
                    return repo.save(score);
                })
                .doOnSuccess(saved ->
                    eventBus.publish(new ScoreRegisteredEvent(
                        principal.schoolId(), principal.userId(),
                        saved.getId(), saved.getStudentId(), saved.getClassTaskId(), saved.getScore()))
                )
        );
    }

    @Override
    public Mono<StudentScore> update(String id, StudentScore updated) {
        return TenantContext.getSchoolId().flatMap(sid ->
            repo.findByIdAndSchoolId(id, sid)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("StudentScore", id)))
                .flatMap(existing -> {
                    existing.setScore(updated.getScore());
                    existing.setComments(updated.getComments());
                    return repo.save(existing);
                })
        );
    }

    @Override
    public Mono<Void>          delete(String id)          { return TenantContext.getSchoolId().flatMap(sid -> repo.deleteByIdAndSchoolId(id, sid)); }
    @Override
    public Flux<StudentScore>  findByStudent(String sid)  { return TenantContext.getSchoolId().flatMapMany(school -> repo.findByStudentIdAndSchoolId(sid, school)); }
    @Override
    public Flux<StudentScore>  findByClassTask(String tid){ return TenantContext.getSchoolId().flatMapMany(school -> repo.findByClassTaskIdAndSchoolId(tid, school)); }
}
