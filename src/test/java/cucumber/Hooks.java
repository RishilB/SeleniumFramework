package cucumber;

import com.rbhatt.selenium.Base.BaseTest;
import com.rbhatt.selenium.utils.PropertyFileReader;
import com.rbhatt.selenium.utils.VideoRecorder;
import io.cucumber.java.After;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.time.Duration;
import java.util.Objects;
import java.util.logging.Logger;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Hooks extends BaseTest {
	
	private static final Logger logger = Logger.getLogger(Hooks.class.getName());
	private final VideoRecorder videoRecorder = new VideoRecorder();
	private String recordVideoFlag;

	@BeforeAll(order = 0)
	public static void cleanVideosFolder(){
		File videosDir = new File("videos");
		if (videosDir.exists() && videosDir.isDirectory()) {
			for (File file : Objects.requireNonNull(videosDir.listFiles())) {
				if (file.isFile()) {
					file.delete();
				}
			}
			logger.info("Videos folder cleaned before execution.");
		}
	}

	@Before
	public void beforeScenario(Scenario scenario) {
		try {
			// Load the video recording flag from the property file
			recordVideoFlag = new PropertyFileReader("src/main/resources/GlobalData.properties").getProperty("recordVideo");

			// Start recording if the flag is set to "On"
			if ("On".equalsIgnoreCase(recordVideoFlag)) {
				videoRecorder.startRecording(scenario.getName().replaceAll(" ", "_"));
				logger.info("Video recording started for scenario: " + scenario.getName());
			}
		} catch (Exception e) {
			logger.severe("Failed to start video recording: " + e.getMessage());
		}
	}

	@After
	public void afterScenario(Scenario scenario) {
		WebDriver driver = BaseTest.getDriver();
		try {
			// Load the video recording flag from the property file
			recordVideoFlag = new PropertyFileReader("src/main/resources/GlobalData.properties").getProperty("recordVideo");

			if ("On".equalsIgnoreCase(recordVideoFlag)) {
				// Wait a few seconds before stopping the recording
				//Thread.sleep(3000); // Adjust the delay as needed
				videoRecorder.stopRecording();
				System.out.println("Video recording stopped for scenario: " + scenario.getName());
			}

			// Capture and attach the screenshot to Allure report for failed scenarios
			if (scenario.isFailed()) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				Allure.addAttachment(scenario.getName(), new ByteArrayInputStream(screenshot));
			}
		} catch (Exception e){
			logger.severe("Error in afterScenario:"+ e.getMessage());
		} finally {
			if(driver != null){
				driver.quit();
			}
		}
	}
}