package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

//@Listeners({com.rbhatt.selenium.TestComponents.Retry.class})
@CucumberOptions(features = "src/test/java/cucumber",
		glue = "com.rbhatt.selenium.StepDefinitions",
		monochrome = true,
		tags = "@Regression",
		plugin={"pretty","html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
