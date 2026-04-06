package pe.edu.fineflow.evaluation.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.fineflow.evaluation.domain.model.StudentScore;
import pe.edu.fineflow.evaluation.domain.port.out.StudentScoreRepositoryPort;
import pe.edu.fineflow.evaluation.infrastructure.adapter.out.persistence.entity.StudentScoreEntity;
import pe.edu.fineflow.evaluation.infrastructure.adapter.out.persistence.repository.StudentScoreR2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StudentScoreRepositoryAdapter implements StudentScoreRepositoryPort {

    private final StudentScoreR2dbcRepository repository;

    @Override
    public Mono<StudentScore> save(StudentScore score) {
        return repository.save(toEntity(score)).map(this::toModel);
    }

    @Override
    public Mono<StudentScore> findByIdAndSchoolId(String id, String schoolId) {
        return repository.findByIdAndSchoolId(id, schoolId).map(this::toModel);
    }

    @Override
    public Flux<StudentScore> findByStudentIdAndSchoolId(String studentId, String schoolId) {
        return repository.findByStudentIdAndSchoolId(studentId, schoolId).map(this::toModel);
    }

    @Override
    public Flux<StudentScore> findByClassTaskIdAndSchoolId(String classTaskId, String schoolId) {
        return repository.findByClassTaskIdAndSchoolId(classTaskId, schoolId).map(this::toModel);
    }

    @Override
    public Mono<Boolean> existsByStudentIdAndClassTaskId(String studentId, String classTaskId) {
        return repository.existsByStudentIdAndClassTaskId(studentId, classTaskId);
    }

    @Override
    public Mono<Void> deleteByIdAndSchoolId(String id, String schoolId) {
        return repository.deleteByIdAndSchoolId(id, schoolId);
    }

    private StudentScoreEntity toEntity(StudentScore m) {
        StudentScoreEntity e = new StudentScoreEntity();
        e.setId(m.getId());
        e.setSchoolId(m.getSchoolId());
        e.setStudentId(m.getStudentId());
        e.setClassTaskId(m.getClassTaskId());
        e.setScore(m.getScore());
        e.setComments(m.getComments());
        e.setRegisteredBy(m.getRegisteredBy());
        return e;
    }

    private StudentScore toModel(StudentScoreEntity e) {
        return new StudentScore(
            e.getId(), e.getSchoolId(), e.getStudentId(), e.getClassTaskId(),
            e.getRegisteredBy(), e.getComments(), e.getScore(),
            e.getRegisteredAt(), e.getUpdatedAt()
        );
    }
}
