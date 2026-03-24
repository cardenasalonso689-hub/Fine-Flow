package pe.edu.fineflow.report.domain.port.out;
import pe.edu.fineflow.report.domain.model.ReportJob;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface ReportJobRepositoryPort {
    Mono<ReportJob> save(ReportJob job);
    Mono<ReportJob> findByIdAndSchoolId(String id, String schoolId);
    Flux<ReportJob> findByRequestedByAndSchoolId(String userId, String schoolId);
    Flux<ReportJob> findPending();
    Mono<Void>      updateStatus(String id, String status, int progress, String filePath);
}
