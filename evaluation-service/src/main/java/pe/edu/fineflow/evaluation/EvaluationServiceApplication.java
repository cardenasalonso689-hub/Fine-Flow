package pe.edu.fineflow.evaluation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"pe.edu.fineflow.evaluation","pe.edu.fineflow.common"})
public class EvaluationServiceApplication {
    public static void main(String[] args) { 
        SpringApplication.run(EvaluationServiceApplication.class, args); 
    }
}
