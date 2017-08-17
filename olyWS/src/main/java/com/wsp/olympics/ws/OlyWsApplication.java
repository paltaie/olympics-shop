package com.wsp.olympics.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OlyWsApplication {
    public static void main(String[] args) {
        System.setProperty("server.port", "8081");
        SpringApplication.run(OlyWsApplication.class, args);
    }
}
