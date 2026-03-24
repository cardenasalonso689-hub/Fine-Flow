package pe.edu.fineflow.innovation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = {"pe.edu.fineflow.innovation","pe.edu.fineflow.common"})
public class InnovationServiceApplication {
    public static void main(String[] args) { SpringApplication.run(InnovationServiceApplication.class, args); }
}
