package pe.edu.fineflow.support;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = {"pe.edu.fineflow.support","pe.edu.fineflow.common"})
public class SupportServiceApplication {
    public static void main(String[] args) { SpringApplication.run(SupportServiceApplication.class, args); }
}
