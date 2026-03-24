package pe.edu.fineflow.report;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
@SpringBootApplication(scanBasePackages = {"pe.edu.fineflow.report","pe.edu.fineflow.common"})
@EnableAsync
public class ReportServiceApplication {
    public static void main(String[] args) { SpringApplication.run(ReportServiceApplication.class, args); }
}
