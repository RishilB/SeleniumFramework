package cucumber;

import com.rbhatt.selenium.TestComponents.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.logging.Logger;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Hooks extends BaseTest {
	
	private static final Logger logger = Logger.getLogger(Hooks.class.getName());

	@After
	public void afterScenario(Scenario scenario) {
		WebDriver driver = BaseTest.getDriver();
		if (scenario.isFailed()) {
			// Capture and attach screenshot to Allure report
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
		}
		driver.quit();
	}
}

