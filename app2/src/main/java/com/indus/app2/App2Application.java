package com.indus.app2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.indus.app2.model") 
@EnableJpaRepositories(basePackages = "com.indus.app2.repository")
public class App2Application {
    public static void main(String[] args) {
        SpringApplication.run(App2Application.class, args);
        System.out.println("Application Started-->");
    }
}
