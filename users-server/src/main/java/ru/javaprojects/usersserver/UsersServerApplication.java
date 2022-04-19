package ru.javaprojects.usersserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UsersServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersServerApplication.class, args);
    }

}
