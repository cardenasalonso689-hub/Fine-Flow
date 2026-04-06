package pe.edu.fineflow.profile.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.fineflow.common.exception.ResourceNotFoundException;
import pe.edu.fineflow.common.tenant.TenantContext;
import pe.edu.fineflow.common.util.UuidGenerator;
import pe.edu.fineflow.profile.application.port.in.ManageGuardianUseCase;
import pe.edu.fineflow.profile.domain.model.Guardian;
import pe.edu.fineflow.profile.domain.port.out.GuardianRepositoryPort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ManageGuardianService implements ManageGuardianUseCase {
    private final GuardianRepositoryPort repository;

    @Override
    public Mono<Guardian> create(Guardian guardian) {
        return TenantContext.getSchoolId().flatMap(schoolId -> {
            guardian.setId(UuidGenerator.generate());
            guardian.setSchoolId(schoolId);
            guardian.setCreatedAt(Instant.now());
            return repository.save(guardian);
        });
    }

    @Override
    public Mono<Guardian> update(String id, Guardian updated) {
        return repository.findById(id)
            .switchIfEmpty(Mono.error(new ResourceNotFoundException("Guardian", id)))
            .flatMap(existing -> {
                existing.setFirstName(updated.getFirstName());
                existing.setLastName(updated.getLastName());
                existing.setRelationship(updated.getRelationship());
                existing.setPhone(updated.getPhone());
                existing.setDocumentNumber(updated.getDocumentNumber());
                existing.setEmail(updated.getEmail());
                existing.setPrimaryContact(updated.isPrimaryContact());
                return repository.save(existing);
            });
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Guardian> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Guardian> findAll() {
        return TenantContext.getSchoolId().flatMapMany(repository::findAllBySchoolId);
    }

    @Override
    public Flux<Guardian> findByStudent(String studentId) {
        return repository.findAllByStudentId(studentId);
    }
}
