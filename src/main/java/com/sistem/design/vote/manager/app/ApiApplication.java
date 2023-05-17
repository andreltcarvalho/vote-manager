package com.sistem.design.vote.manager.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiApplication {

    Logger logger = LoggerFactory.getLogger(ApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
