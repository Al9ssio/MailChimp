package stepdefinitions_edge;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/registration.feature",
        glue = "stepdefinitions_edge"
)
public class RunCucumberStepsEdge {
}
