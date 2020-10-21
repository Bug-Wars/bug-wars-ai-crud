package codecamp.bug.wars.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
public class BugWarsAiCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(BugWarsAiCrudApplication.class, args);
	}

}
