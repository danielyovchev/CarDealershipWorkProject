package com.project.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.example.api", "com.price.api", "com.salary.api"})
@ComponentScan("com.project")
@EnableJpaRepositories(basePackages = {"com.project.data"})
@EntityScan(
        basePackages = "com.project.data"
)
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

}
