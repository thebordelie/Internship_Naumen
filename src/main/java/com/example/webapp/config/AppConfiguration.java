package com.example.webapp.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
        "com.example.webapp.controller",
        "com.example.webapp.service",
        "com.example.webapp.reader"
})
public class AppConfiguration {

}
