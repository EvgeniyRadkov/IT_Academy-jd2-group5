package com.gmail.radzkovevgeni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.gmail.radzkovevgeni.*")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
