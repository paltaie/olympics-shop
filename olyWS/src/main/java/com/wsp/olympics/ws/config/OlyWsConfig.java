package com.wsp.olympics.ws.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.wsp.olympics")
@EnableAutoConfiguration(exclude = SqlInitializationAutoConfiguration.class)
public class OlyWsConfig {
}
