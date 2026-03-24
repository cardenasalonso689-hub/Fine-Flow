package pe.edu.fineflow.report.infrastructure.adapter.in.web;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.fineflow.report.application.port.in.RequestReportUseCase;
import pe.edu.fineflow.report.domain.model.ReportJob;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@Tag(name = "Reportes", description = "Generación asíncrona de reportes PDF y Excel")
public class ReportController {

    private final RequestReportUseCase useCase;
    public ReportController(RequestReportUseCase useCase) { this.useCase = useCase; }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    public Mono<ReportJob> requestReport(@RequestBody Map<String, String> body) {
        return useCase.request(body.get("reportType"), body.getOrDefault("format","PDF"), body.get("parameters"));
    }

    @GetMapping("/{jobId}/status")
    @PreAuthorize("isAuthenticated()")
    public Mono<ReportJob> getStatus(@PathVariable String jobId) {
        return useCase.getStatus(jobId);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public Flux<ReportJob> myJobs() { return useCase.myJobs(); }

    @GetMapping("/{jobId}/download")
    @PreAuthorize("isAuthenticated()")
    public Mono<ResponseEntity<byte[]>> download(@PathVariable String jobId) {
        return useCase.download(jobId)
                .map(bytes -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="report-" + jobId + ".pdf"")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(bytes));
    }
}
