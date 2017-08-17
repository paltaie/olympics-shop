package com.wsp.olympics.supplierapp;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:olySupplier.properties")
public class OlySupplierApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(OlySupplierApplication.class)
                .headless(false)
                .run(args);
    }
}
