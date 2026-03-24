package pe.edu.fineflow.evaluation.application.service;
import org.springframework.stereotype.Service;
import pe.edu.fineflow.common.event.AttendanceRecordedEvent;
import pe.edu.fineflow.common.event.EventBus;
import pe.edu.fineflow.common.exception.BusinessException;
import pe.edu.fineflow.common.tenant.TenantContext;
import pe.edu.fineflow.common.util.UuidGenerator;
import pe.edu.fineflow.evaluation.application.port.in.RecordAttendanceUseCase;
import pe.edu.fineflow.evaluation.domain.model.Attendance;
import pe.edu.fineflow.evaluation.domain.port.out.AttendanceRepositoryPort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordAttendanceService implements RecordAttendanceUseCase {

    private final AttendanceRepositoryPort repo;
    private final EventBus eventBus;

    public RecordAttendanceService(AttendanceRepositoryPort repo, EventBus eventBus) {
        this.repo = repo; this.eventBus = eventBus;
    }

    @Override
    public Mono<Attendance> recordSingle(Attendance attendance) {
        return TenantContext.getPrincipal().flatMap(principal ->
            repo.existsByStudentIdAndDateAndAssignment(
                    attendance.getStudentId(), attendance.getAttendanceDate(),
                    attendance.getCourseAssignmentId(), principal.schoolId())
                .flatMap(exists -> {
                    if (exists) return Mono.error(BusinessException.conflict(
                            "ATTENDANCE_DUPLICATE", "Ya existe un registro para este alumno en esta fecha."));
                    attendance.setId(UuidGenerator.generate());
                    attendance.setSchoolId(principal.schoolId());
                    attendance.setRegisteredBy(principal.userId());
                    attendance.setCreatedAt(Instant.now());
                    return repo.save(attendance);
                })
                .doOnSuccess(saved ->
                    eventBus.publish(new AttendanceRecordedEvent(
                        principal.schoolId(), principal.userId(), saved.getId(),
                        saved.getStudentId(), saved.getStatus(),
                        saved.getAttendanceDate().toString()))
                )
        );
    }

    @Override
    public Flux<Attendance> recordBulk(List<Attendance> list) {
        return TenantContext.getPrincipal().flatMapMany(principal -> {
            List<Attendance> enriched = list.stream().map(a -> {
                a.setId(UuidGenerator.generate());
                a.setSchoolId(principal.schoolId());
                a.setRegisteredBy(principal.userId());
                a.setCreatedAt(Instant.now());
                return a;
            }).collect(Collectors.toList());
            return repo.saveAll(enriched)
                    .doOnNext(saved -> eventBus.publish(new AttendanceRecordedEvent(
                            principal.schoolId(), principal.userId(), saved.getId(),
                            saved.getStudentId(), saved.getStatus(),
                            saved.getAttendanceDate().toString())));
        });
    }

    @Override
    public Mono<Attendance> recordQrEntry(String qrToken, String schoolId) {
        // TODO: validar HMAC-SHA256 del token, buscar student por qr_secret
        return Mono.error(new UnsupportedOperationException("QR validation not implemented yet"));
    }

    @Override
    public Flux<Attendance> findByStudent(String studentId) {
        return TenantContext.getSchoolId().flatMapMany(sid -> repo.findByStudentIdAndSchoolId(studentId, sid));
    }

    @Override
    public Flux<Attendance> findByDate(LocalDate date) {
        return TenantContext.getSchoolId().flatMapMany(sid -> repo.findByDateAndSchoolId(date, sid));
    }
}
