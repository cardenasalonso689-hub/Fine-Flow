package pe.edu.fineflow.profile.infrastructure.adapter.out.persistence;
import org.springframework.stereotype.Component;
import pe.edu.fineflow.profile.domain.model.Student;
import pe.edu.fineflow.profile.domain.port.out.StudentRepositoryPort;
import pe.edu.fineflow.profile.infrastructure.adapter.out.persistence.mapper.StudentPersistenceMapper;
import pe.edu.fineflow.profile.infrastructure.adapter.out.persistence.repository.StudentR2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** Adaptador de salida: traduce entre Domain Model y R2DBC Entity. */
@Component
public class StudentPersistenceAdapter implements StudentRepositoryPort {

    private final StudentR2dbcRepository repo;
    private final StudentPersistenceMapper mapper;

    public StudentPersistenceAdapter(StudentR2dbcRepository repo, StudentPersistenceMapper mapper) {
        this.repo   = repo;
        this.mapper = mapper;
    }

    @Override public Mono<Student> save(Student student) {
        return repo.save(mapper.toEntity(student)).map(mapper::toDomain);
    }
    @Override public Mono<Student> findByIdAndSchoolId(String id, String schoolId) {
        return repo.findByIdAndSchoolId(id, schoolId).map(mapper::toDomain);
    }
    @Override public Flux<Student> findAllBySchoolId(String schoolId) {
        return repo.findAllBySchoolId(schoolId).map(mapper::toDomain);
    }
    @Override public Flux<Student> findAllBySectionIdAndSchoolId(String sectionId, String schoolId) {
        return repo.findAllBySectionIdAndSchoolId(sectionId, schoolId).map(mapper::toDomain);
    }
    @Override public Mono<Boolean> existsByDocumentNumberAndSchoolId(String doc, String schoolId) {
        return repo.existsByDocumentNumberAndSchoolId(doc, schoolId);
    }
    @Override public Mono<Void> deleteByIdAndSchoolId(String id, String schoolId) {
        return repo.deleteByIdAndSchoolId(id, schoolId);
    }
}
