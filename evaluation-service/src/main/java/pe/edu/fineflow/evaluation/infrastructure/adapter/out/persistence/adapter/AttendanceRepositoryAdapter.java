package pe.edu.fineflow.evaluation.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.fineflow.evaluation.domain.model.Attendance;
import pe.edu.fineflow.evaluation.domain.port.out.AttendanceRepositoryPort;
import pe.edu.fineflow.evaluation.infrastructure.adapter.out.persistence.entity.AttendanceEntity;
import pe.edu.fineflow.evaluation.infrastructure.adapter.out.persistence.repository.AttendanceR2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AttendanceRepositoryAdapter implements AttendanceRepositoryPort {

    private final AttendanceR2dbcRepository repository;

    @Override
    public Mono<Attendance> save(Attendance a) {
        return repository.save(toEntity(a)).map(this::toModel);
    }

    @Override
    public Flux<Attendance> saveAll(List<Attendance> list) {
        return Flux.fromIterable(list)
            .map(this::toEntity)
            .collectList()
            .flatMapMany(repository::saveAll)
            .map(this::toModel);
    }

    @Override
    public Mono<Attendance> findByIdAndSchoolId(String id, String schoolId) {
        return repository.findByIdAndSchoolId(id, schoolId).map(this::toModel);
    }

    @Override
    public Flux<Attendance> findByStudentIdAndSchoolId(String studentId, String schoolId) {
        return repository.findByStudentIdAndSchoolId(studentId, schoolId).map(this::toModel);
    }

    @Override
    public Flux<Attendance> findByDateAndSchoolId(LocalDate date, String schoolId) {
        return repository.findByAttendanceDateAndSchoolId(date, schoolId).map(this::toModel);
    }

    @Override
    public Mono<Boolean> existsByStudentIdAndDateAndAssignment(String studentId, LocalDate date, String assignmentId, String schoolId) {
        return repository.existsByStudentIdAndDateAndAssignment(studentId, date, assignmentId, schoolId);
    }

    private AttendanceEntity toEntity(Attendance m) {
        AttendanceEntity e = new AttendanceEntity();
        e.setId(m.getId());
        e.setSchoolId(m.getSchoolId());
        e.setStudentId(m.getStudentId());
        e.setCourseAssignmentId(m.getCourseAssignmentId());
        e.setAttendanceDate(m.getAttendanceDate());
        e.setStatus(m.getStatus());
        e.setCheckInTime(m.getCheckInTime());
        e.setRecordMethod(m.getRecordMethod());
        e.setJustificationReason(m.getJustificationReason());
        e.setRegisteredBy(m.getRegisteredBy());
        return e;
    }

    private Attendance toModel(AttendanceEntity e) {
        return new Attendance(
            e.getId(), e.getSchoolId(), e.getStudentId(), e.getCourseAssignmentId(),
            e.getAttendanceDate(), e.getStatus(), e.getCheckInTime(), e.getRecordMethod(),
            e.getJustificationReason(), e.getRegisteredBy(), e.getCreatedAt()
        );
    }
}
