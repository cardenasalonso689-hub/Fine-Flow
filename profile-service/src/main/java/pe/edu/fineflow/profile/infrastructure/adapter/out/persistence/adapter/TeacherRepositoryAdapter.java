package pe.edu.fineflow.profile.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.fineflow.profile.domain.model.Teacher;
import pe.edu.fineflow.profile.domain.port.out.TeacherRepositoryPort;
import pe.edu.fineflow.profile.infrastructure.adapter.out.persistence.entity.TeacherEntity;
import pe.edu.fineflow.profile.infrastructure.adapter.out.persistence.repository.TeacherR2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TeacherRepositoryAdapter implements TeacherRepositoryPort {

    private final TeacherR2dbcRepository repository;

    @Override
    public Mono<Teacher> save(Teacher teacher) {
        TeacherEntity entity = toEntity(teacher);
        return repository.save(entity).map(this::toModel);
    }

    @Override
    public Mono<Teacher> findByIdAndSchoolId(String id, String schoolId) {
        return repository.findByIdAndSchoolId(id, schoolId).map(this::toModel);
    }

    @Override
    public Flux<Teacher> findAllBySchoolId(String schoolId) {
        return repository.findAllBySchoolId(schoolId).map(this::toModel);
    }

    @Override
    public Mono<Boolean> existsByDocumentNumberAndSchoolId(String documentNumber, String schoolId) {
        return repository.existsByDocumentNumberAndSchoolId(documentNumber, schoolId);
    }

    @Override
    public Mono<Void> deleteByIdAndSchoolId(String id, String schoolId) {
        return repository.deleteByIdAndSchoolId(id, schoolId);
    }

    private TeacherEntity toEntity(Teacher model) {
        TeacherEntity entity = new TeacherEntity();
        entity.setId(model.getId());
        entity.setSchoolId(model.getSchoolId());
        entity.setUserId(model.getUserId());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setDocumentNumber(model.getDocumentNumber());
        entity.setSpecialty(model.getSpecialty());
        entity.setStatus(model.getStatus());
        return entity;
    }

    private Teacher toModel(TeacherEntity entity) {
        return new Teacher(
            entity.getId(),
            entity.getSchoolId(),
            entity.getUserId(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getDocumentNumber(),
            entity.getSpecialty(),
            entity.getStatus(),
            entity.getCreatedAt()
        );
    }
}
