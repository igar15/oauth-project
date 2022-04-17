package ru.javaprojects.resourseserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ResourseServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourseServerApplication.class, args);
    }

}
