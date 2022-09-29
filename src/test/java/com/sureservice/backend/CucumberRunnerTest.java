package com.sureservice.backend;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"pretty","json:target/cucumber.json"},
        glue={"cucumber"},
        features="src/test/resources/features"
)
public class CucumberRunnerTest {
}
