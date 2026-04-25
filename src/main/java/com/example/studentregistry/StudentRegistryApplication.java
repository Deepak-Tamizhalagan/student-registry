package com.example.studentregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StudentRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentRegistryApplication.class, args);
    }
}