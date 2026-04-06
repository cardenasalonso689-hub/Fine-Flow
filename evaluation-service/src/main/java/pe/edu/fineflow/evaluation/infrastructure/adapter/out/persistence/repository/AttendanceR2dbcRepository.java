package pe.edu.fineflow.evaluation.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.evaluation.infrastructure.adapter.out.persistence.entity.AttendanceEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDate;

@Repository
public interface AttendanceR2dbcRepository extends R2dbcRepository<AttendanceEntity, String> {
    Mono<AttendanceEntity> findByIdAndSchoolId(String id, String schoolId);
    Flux<AttendanceEntity> findByStudentIdAndSchoolId(String studentId, String schoolId);
    Flux<AttendanceEntity> findByAttendanceDateAndSchoolId(LocalDate date, String schoolId);
    
    @Query("SELECT * FROM ATTENDANCES WHERE student_id = :studentId AND attendance_date = :date AND course_assignment_id = :assignmentId AND school_id = :schoolId LIMIT 1")
    Mono<Boolean> existsByStudentIdAndDateAndAssignment(String studentId, LocalDate date, String assignmentId, String schoolId);
}
