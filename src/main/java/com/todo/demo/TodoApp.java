package com.todo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class TodoApp {
    public static void main(String[] args) {
        SpringApplication.run(TodoApp.class, args);
    }
}
