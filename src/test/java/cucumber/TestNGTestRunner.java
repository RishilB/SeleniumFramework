package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Test
@CucumberOptions(
		features = "src/test/java/cucumber",
		glue = "com.rbhatt.selenium.StepDefinitions",
		monochrome = true,
		tags = "@Regression",
		publish = true,
		plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})

public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
