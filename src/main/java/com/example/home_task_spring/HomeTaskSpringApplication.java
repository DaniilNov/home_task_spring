package com.example.home_task_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class HomeTaskSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeTaskSpringApplication.class, args);
    }
}

