package com.believe.webFlux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan({"com.believe.webFlux.core.model", "com.believe.webFlux.*.domain"})
@EnableJpaRepositories("com.believe.webFlux.*.repositories")
@SpringBootApplication
public class WebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxApplication.class, args);
    }
}
