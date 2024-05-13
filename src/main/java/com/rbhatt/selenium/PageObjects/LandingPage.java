package com.rbhatt.selenium.PageObjects;

import com.rbhatt.selenium.AbstractCompoments.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

public class LandingPage extends AbstractComponent {
	
	@FindBy(id="userEmail")
	WebElement userEmailEle;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement loginBtnEle;
	
	WebDriver driver;
	public LandingPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void goTo(String URL){
		driver.get(URL);
	}
	
	public void loginAction(String email, String password){
		userEmailEle.sendKeys(email);
		passwordEle.sendKeys(password);
		loginBtnEle.click();
		
	}
	

	
	
}
