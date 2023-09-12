package com.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableFeignClients
@EnableMongoRepositories(basePackages = "com.dev.repository")
public class MsMarathonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsMarathonApplication.class, args);
    }

}
