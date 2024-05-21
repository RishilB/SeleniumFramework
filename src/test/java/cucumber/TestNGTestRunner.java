package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber",
		glue = "com.rbhatt.selenium.StepDefinitions",
		monochrome = true,
		tags = "@Regression",
		plugin = {"pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
