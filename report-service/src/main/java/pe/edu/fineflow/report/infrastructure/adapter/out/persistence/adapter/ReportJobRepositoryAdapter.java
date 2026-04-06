package pe.edu.fineflow.report.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.fineflow.report.domain.model.ReportJob;
import pe.edu.fineflow.report.domain.port.out.ReportJobRepositoryPort;
import pe.edu.fineflow.report.infrastructure.adapter.out.persistence.entity.ReportJobEntity;
import pe.edu.fineflow.report.infrastructure.adapter.out.persistence.repository.ReportJobR2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ReportJobRepositoryAdapter implements ReportJobRepositoryPort {

    private final ReportJobR2dbcRepository repository;

    @Override
    public Mono<ReportJob> save(ReportJob job) {
        return repository.save(toEntity(job)).map(this::toModel);
    }

    @Override
    public Mono<ReportJob> findByIdAndSchoolId(String id, String schoolId) {
        return repository.findByIdAndSchoolId(id, schoolId).map(this::toModel);
    }

    @Override
    public Flux<ReportJob> findByRequestedByAndSchoolId(String userId, String schoolId) {
        return repository.findByRequestedByAndSchoolId(userId, schoolId).map(this::toModel);
    }

    @Override
    public Flux<ReportJob> findPending() {
        return repository.findByStatus("PENDING").map(this::toModel);
    }

    @Override
    public Mono<Void> updateStatus(String id, String status, int progress, String filePath) {
        return repository.findById(id)
            .flatMap(e -> {
                e.setStatus(status);
                e.setProgressPct(progress);
                e.setFilePath(filePath);
                return repository.save(e);
            })
            .then();
    }

    private ReportJobEntity toEntity(ReportJob m) {
        ReportJobEntity e = new ReportJobEntity();
        e.setId(m.getId());
        e.setSchoolId(m.getSchoolId());
        e.setRequestedBy(m.getRequestedBy());
        e.setReportType(m.getReportType());
        e.setFormat(m.getFormat());
        e.setParametersJson(m.getParametersJson());
        e.setStatus(m.getStatus());
        e.setFilePath(m.getFilePath());
        e.setErrorDetail(m.getErrorDetail());
        e.setFileSizeKb(m.getFileSizeKb());
        e.setProgressPct(m.getProgressPct());
        e.setRequestedAt(m.getRequestedAt());
        e.setStartedAt(m.getStartedAt());
        e.setCompletedAt(m.getCompletedAt());
        e.setExpiresAt(m.getExpiresAt());
        e.setDownloadCount(m.getDownloadCount());
        return e;
    }

    private ReportJob toModel(ReportJobEntity e) {
        return new ReportJob(
            e.getId(), e.getSchoolId(), e.getRequestedBy(), e.getReportType(),
            e.getFormat(), e.getParametersJson(), e.getStatus(), e.getFilePath(),
            e.getErrorDetail(), e.getFileSizeKb(), e.getProgressPct(),
            e.getRequestedAt(), e.getStartedAt(), e.getCompletedAt(), e.getExpiresAt(),
            e.getDownloadCount()
        );
    }
}
