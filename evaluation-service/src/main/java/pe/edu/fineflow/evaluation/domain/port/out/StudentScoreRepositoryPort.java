package pe.edu.fineflow.evaluation.domain.port.out;
import pe.edu.fineflow.evaluation.domain.model.StudentScore;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface StudentScoreRepositoryPort {
    Mono<StudentScore> save(StudentScore score);
    Mono<StudentScore> findByIdAndSchoolId(String id, String schoolId);
    Flux<StudentScore> findByStudentIdAndSchoolId(String studentId, String schoolId);
    Flux<StudentScore> findByClassTaskIdAndSchoolId(String classTaskId, String schoolId);
    Mono<Boolean>      existsByStudentIdAndClassTaskId(String studentId, String classTaskId);
    Mono<Void>         deleteByIdAndSchoolId(String id, String schoolId);
}
