package com.sureservice.backend.cucumber.step.definition;

import com.sureservice.backend.SureserviceBackendApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = SureserviceBackendApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = SureserviceBackendApplication.class,
        loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {
}
