package pe.edu.fineflow.report.application.service;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pe.edu.fineflow.common.exception.ResourceNotFoundException;
import pe.edu.fineflow.common.tenant.TenantContext;
import pe.edu.fineflow.common.util.UuidGenerator;
import pe.edu.fineflow.report.application.port.in.RequestReportUseCase;
import pe.edu.fineflow.report.domain.model.ReportJob;
import pe.edu.fineflow.report.domain.port.out.ReportJobRepositoryPort;
import pe.edu.fineflow.report.infrastructure.generator.PdfReportGenerator;
import pe.edu.fineflow.report.infrastructure.generator.ExcelReportGenerator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Instant;

@Service
public class ReportRequestService implements RequestReportUseCase {

    private final ReportJobRepositoryPort repo;
    private final PdfReportGenerator pdfGen;
    private final ExcelReportGenerator excelGen;

    public ReportRequestService(ReportJobRepositoryPort repo,
                                 PdfReportGenerator pdfGen,
                                 ExcelReportGenerator excelGen) {
        this.repo     = repo;
        this.pdfGen   = pdfGen;
        this.excelGen = excelGen;
    }

    @Override
    public Mono<ReportJob> request(String reportType, String format, String parametersJson) {
        return TenantContext.getPrincipal().flatMap(principal -> {
            ReportJob job = new ReportJob();
            job.setId(UuidGenerator.generate());
            job.setSchoolId(principal.schoolId());
            job.setRequestedBy(principal.userId());
            job.setReportType(reportType);
            job.setFormat(format);
            job.setParametersJson(parametersJson);
            job.setStatus("PENDING");
            job.setProgressPct(0);
            job.setRequestedAt(Instant.now());
            job.setExpiresAt(Instant.now().plusSeconds(72 * 3600)); // 72h default
            return repo.save(job).doOnSuccess(this::processAsync);
        });
    }

    @Async
    public void processAsync(ReportJob job) {
        // Procesamiento asíncrono en thread pool separado
        try {
            repo.updateStatus(job.getId(), "PROCESSING", 10, null).subscribe();
            byte[] bytes;
            if ("PDF".equals(job.getFormat())) {
                bytes = pdfGen.generate(job);
            } else {
                bytes = excelGen.generate(job);
            }
            String path = "/reports/" + job.getSchoolId() + "/" + job.getId() + "." + job.getFormat().toLowerCase();
            // In real impl: save bytes to file storage (S3/MinIO)
            repo.updateStatus(job.getId(), "COMPLETED", 100, path).subscribe();
        } catch (Exception ex) {
            repo.updateStatus(job.getId(), "FAILED", 0, null).subscribe();
        }
    }

    @Override
    public Mono<ReportJob> getStatus(String jobId) {
        return TenantContext.getSchoolId()
                .flatMap(sid -> repo.findByIdAndSchoolId(jobId, sid)
                        .switchIfEmpty(Mono.error(new ResourceNotFoundException("ReportJob", jobId))));
    }

    @Override
    public Flux<ReportJob> myJobs() {
        return TenantContext.getPrincipal().flatMapMany(p -> repo.findByRequestedByAndSchoolId(p.userId(), p.schoolId()));
    }

    @Override
    public Mono<byte[]> download(String jobId) {
        return getStatus(jobId).flatMap(job -> {
            if (!"COMPLETED".equals(job.getStatus())) {
                return Mono.error(new IllegalStateException("El reporte aún no está listo."));
            }
            // In real impl: read file from storage
            return Mono.just(new byte[0]);
        });
    }
}
