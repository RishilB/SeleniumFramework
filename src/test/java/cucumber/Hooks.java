package cucumber;

import com.rbhatt.selenium.TestComponents.BaseTest;
import io.cucumber.java.After;
import java.util.logging.Logger;

public class Hooks extends BaseTest {
	
	private static final Logger logger = Logger.getLogger(Hooks.class.getName());

	@After
	public void tearDown(){
		 //Call the tearDown method from BaseTest
		logger.info("Tear down started");
		super.tearDown();
		logger.info("Tear down completed");
		}
	}

