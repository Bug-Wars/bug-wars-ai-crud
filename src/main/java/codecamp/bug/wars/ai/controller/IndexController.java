package codecamp.bug.wars.ai.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class IndexController {
    @Value("${eureka.instance.hostname:}")
    String hostname;

    @Value("${eureka.client.serviceUrl.defaultZone:}")
    String eurekaUrl;

    @Value("${spring.profiles.active:default}")
    String profile;

    @Value("${domain.name:}")
    String domainName;

    @GetMapping
    public String getIndex() {
        return String.join("<BR>", Arrays.asList(
                "Bug Wars AI CRUD Repository",
                "Hostname: " + hostname,
                "Eureka hostname; " + eurekaUrl,
                "Spring Profile: " + profile,
                "DOMAIN NAME: " + domainName
        ));
    }
}
