package com.rbhatt.selenium.tests;

import com.rbhatt.selenium.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidations extends BaseTest {
	
	@Test
	public void standaloneTest() throws IOException {
		
		//1. Perform Login with incorrect Password
		landingPage.loginAction("risshilbhatt@gmail.com", "Test@1234");
		Assert.assertEquals("Incorrect email or password.",landingPage.getLoginErrorMessage());
		
	}
}
