package com.example.selfdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SelfDevApplication {
    public static void main(String[] args) {
        SpringApplication.run(SelfDevApplication.class, args);
    }
}
