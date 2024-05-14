package com.rbhatt.selenium.AbstractCompoments;

import com.rbhatt.selenium.PageObjects.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent{
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement goToCartBtn;
	
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitForElementToAppear(By findBy){
		WebDriverWait waitForElementAppear = new WebDriverWait(driver, Duration.ofSeconds(10));
		waitForElementAppear.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDisappear(By findBy){
		WebDriverWait waitForElementDisappear = new WebDriverWait(driver, Duration.ofSeconds(10));
		waitForElementDisappear.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public CartPage goToCart(){
		goToCartBtn.click();
		return new CartPage(driver);
	}
	

}
