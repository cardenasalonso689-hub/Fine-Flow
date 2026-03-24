package pe.edu.fineflow.profile.domain.port.out;
import pe.edu.fineflow.profile.domain.model.Teacher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface TeacherRepositoryPort {
    Mono<Teacher> save(Teacher teacher);
    Mono<Teacher> findByIdAndSchoolId(String id, String schoolId);
    Flux<Teacher> findAllBySchoolId(String schoolId);
    Mono<Boolean> existsByDocumentNumberAndSchoolId(String documentNumber, String schoolId);
    Mono<Void>    deleteByIdAndSchoolId(String id, String schoolId);
}
