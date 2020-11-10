package codecamp.bug.wars.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class BugWarsAiCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(BugWarsAiCrudApplication.class, args);
	}

}
