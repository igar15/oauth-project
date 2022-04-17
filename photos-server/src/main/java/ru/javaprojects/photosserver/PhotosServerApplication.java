package ru.javaprojects.photosserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PhotosServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotosServerApplication.class, args);
    }

}
