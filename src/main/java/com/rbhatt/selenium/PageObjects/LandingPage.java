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
	
	@FindBy(css="div[aria-label*='Incorrect']")
	WebElement errorMessageEle;
	
	WebDriver driver;
	public LandingPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void goTo(){
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalouge loginAction(String email, String password){
		userEmailEle.sendKeys(email);
		passwordEle.sendKeys(password);
		loginBtnEle.click();
		return new ProductCatalouge(driver);
	}
	
	public String getLoginErrorMessage(){
		waitForWebElementToVisible(errorMessageEle);
		return errorMessageEle.getText();
	}
}
