package pe.edu.fineflow.profile.application.port.in;
import pe.edu.fineflow.profile.domain.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface ManageStudentUseCase {
    Mono<Student> create(Student student);
    Mono<Student> update(String id, Student student);
    Mono<Void>    delete(String id);
    Mono<Student> findById(String id);
    Flux<Student> findAllBySection(String sectionId);
    Flux<Student> search(String query);
}
