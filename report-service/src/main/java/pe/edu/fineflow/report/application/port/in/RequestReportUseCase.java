package pe.edu.fineflow.report.application.port.in;
import pe.edu.fineflow.report.domain.model.ReportJob;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface RequestReportUseCase {
    Mono<ReportJob> request(String reportType, String format, String parametersJson);
    Mono<ReportJob> getStatus(String jobId);
    Flux<ReportJob> myJobs();
    Mono<byte[]>    download(String jobId);
}
