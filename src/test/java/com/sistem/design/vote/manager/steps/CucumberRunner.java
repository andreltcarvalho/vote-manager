package com.sistem.design.vote.manager.steps;

import com.sistem.design.vote.manager.app.ApiApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(classes = ApiApplication.class)
@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:features"},
        glue = {"com.sistem.design.vote.manager.steps"})
@ActiveProfiles("test")
public class CucumberRunner {
}
