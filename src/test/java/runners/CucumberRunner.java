package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "classpath:features",
        glue = "steps",
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
}