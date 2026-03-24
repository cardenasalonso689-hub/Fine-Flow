package pe.edu.fineflow.evaluation.domain.port.out;
import pe.edu.fineflow.evaluation.domain.model.Attendance;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDate;
public interface AttendanceRepositoryPort {
    Mono<Attendance> save(Attendance a);
    Flux<Attendance> saveAll(java.util.List<Attendance> list);
    Mono<Attendance> findByIdAndSchoolId(String id, String schoolId);
    Flux<Attendance> findByStudentIdAndSchoolId(String studentId, String schoolId);
    Flux<Attendance> findByDateAndSchoolId(LocalDate date, String schoolId);
    Mono<Boolean>   existsByStudentIdAndDateAndAssignment(String studentId, LocalDate date, String assignmentId, String schoolId);
}
