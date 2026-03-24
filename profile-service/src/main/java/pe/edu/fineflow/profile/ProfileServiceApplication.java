package pe.edu.fineflow.profile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = {"pe.edu.fineflow.profile","pe.edu.fineflow.common"})
public class ProfileServiceApplication {
    public static void main(String[] args) { SpringApplication.run(ProfileServiceApplication.class, args); }
}
