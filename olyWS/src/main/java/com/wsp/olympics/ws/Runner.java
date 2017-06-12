package com.wsp.olympics.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Runner {
    public static void main(String[] args) {
        System.setProperty("server.port", "8081");
        SpringApplication.run(Runner.class, args);
    }
}
