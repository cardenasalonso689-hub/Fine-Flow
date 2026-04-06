package pe.edu.fineflow.academic.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.fineflow.academic.application.port.in.ManageAcademicPeriodUseCase;
import pe.edu.fineflow.academic.domain.model.AcademicPeriod;
import pe.edu.fineflow.academic.domain.port.out.AcademicPeriodRepositoryPort;
import pe.edu.fineflow.common.tenant.TenantContext;
import pe.edu.fineflow.common.util.UuidGenerator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ManageAcademicPeriodService implements ManageAcademicPeriodUseCase {
    private final AcademicPeriodRepositoryPort repository;

    @Override
    public Mono<AcademicPeriod> create(AcademicPeriod period) {
        return TenantContext.getSchoolId().flatMap(schoolId -> {
            period.setId(UuidGenerator.generate());
            period.setSchoolId(schoolId);
            period.setIsActive(0);
            period.setCreatedAt(Instant.now());
            return repository.save(period);
        });
    }

    @Override
    public Mono<AcademicPeriod> update(String id, AcademicPeriod updated) {
        return repository.findById(id)
            .flatMap(existing -> {
                existing.setName(updated.getName());
                existing.setPeriodType(updated.getPeriodType());
                existing.setStartDate(updated.getStartDate());
                existing.setEndDate(updated.getEndDate());
                existing.setIsActive(updated.getIsActive());
                return repository.save(existing);
            });
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<AcademicPeriod> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<AcademicPeriod> findAll() {
        return TenantContext.getSchoolId().flatMapMany(repository::findAllBySchoolId);
    }

    @Override
    public Flux<AcademicPeriod> findBySchoolYear(String schoolYearId) {
        return repository.findAllBySchoolYearId(schoolYearId);
    }
}
