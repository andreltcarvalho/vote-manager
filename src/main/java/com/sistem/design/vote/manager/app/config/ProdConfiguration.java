package com.sistem.design.vote.manager.app.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfiguration implements CommandLineRunner {


    @Override
    public void run(String... args) {

    }
}