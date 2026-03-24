package pe.edu.fineflow.evaluation.application.port.in;

import pe.edu.fineflow.evaluation.domain.model.Attendance;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDate;
import java.util.List;

public interface RecordAttendanceUseCase {
    Mono<Attendance>  recordSingle(Attendance attendance);
    Flux<Attendance>  recordBulk(List<Attendance> list);
    Mono<Attendance>  recordQrEntry(String qrToken, String schoolId);
    Flux<Attendance>  findByStudent(String studentId);
    Flux<Attendance>  findByDate(LocalDate date);
}
