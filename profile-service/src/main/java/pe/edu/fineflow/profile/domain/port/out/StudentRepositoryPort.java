package pe.edu.fineflow.profile.domain.port.out;
import pe.edu.fineflow.profile.domain.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface StudentRepositoryPort {
    Mono<Student> save(Student student);
    Mono<Student> findByIdAndSchoolId(String id, String schoolId);
    Flux<Student> findAllBySchoolId(String schoolId);
    Flux<Student> findAllBySectionIdAndSchoolId(String sectionId, String schoolId);
    Mono<Boolean> existsByDocumentNumberAndSchoolId(String documentNumber, String schoolId);
    Mono<Void>    deleteByIdAndSchoolId(String id, String schoolId);
}
