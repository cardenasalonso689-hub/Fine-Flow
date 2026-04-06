package pe.edu.fineflow.academic.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.fineflow.academic.application.port.in.ManageSchoolYearUseCase;
import pe.edu.fineflow.academic.domain.model.SchoolYear;
import pe.edu.fineflow.academic.domain.port.out.SchoolYearRepositoryPort;
import pe.edu.fineflow.common.tenant.TenantContext;
import pe.edu.fineflow.common.util.UuidGenerator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ManageSchoolYearService implements ManageSchoolYearUseCase {
    private final SchoolYearRepositoryPort repository;

    @Override
    public Mono<SchoolYear> create(SchoolYear year) {
        return TenantContext.getSchoolId().flatMap(schoolId -> {
            year.setId(UuidGenerator.generate());
            year.setSchoolId(schoolId);
            year.setIsActive(1);
            year.setCreatedAt(Instant.now());
            return repository.save(year);
        });
    }

    @Override
    public Mono<SchoolYear> update(String id, SchoolYear updated) {
        return TenantContext.getSchoolId().flatMap(schoolId ->
            repository.findById(id)
                .flatMap(existing -> {
                    existing.setName(updated.getName());
                    existing.setGradeNumber(updated.getGradeNumber());
                    existing.setCalendarYear(updated.getCalendarYear());
                    existing.setAcademicLevelId(updated.getAcademicLevelId());
                    existing.setIsActive(updated.getIsActive());
                    return repository.save(existing);
                })
        );
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<SchoolYear> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<SchoolYear> findAll() {
        return TenantContext.getSchoolId().flatMapMany(repository::findAllBySchoolId);
    }

    @Override
    public Flux<SchoolYear> findByAcademicLevel(String academicLevelId) {
        return repository.findAllByAcademicLevelId(academicLevelId);
    }
}
