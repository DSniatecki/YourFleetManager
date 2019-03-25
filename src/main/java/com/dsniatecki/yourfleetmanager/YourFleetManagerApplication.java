package com.dsniatecki.yourfleetmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
@EnableWebMvc
public class YourFleetManagerApplication {

    public static void main(String[] args) {
       SpringApplication.run(YourFleetManagerApplication.class, args);
    }
}
