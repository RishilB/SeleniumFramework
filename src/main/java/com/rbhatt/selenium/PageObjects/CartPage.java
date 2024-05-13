package com.rbhatt.selenium.PageObjects;

import com.rbhatt.selenium.AbstractCompoments.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
	
	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> cartProductsElements;
	
	//List<WebElement> cartProductsElements = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
	//boolean isAvailable = cartProductsElements.stream().anyMatch(product -> product.getText().contains(productName));
	//Assert.assertTrue(isAvailable);
    //driver.findElement(By.cssSelector("li[class='totalRow'] button[type='button']")).click();
	
	WebDriver driver;
	public CartPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public Boolean isProductAvailable(List<WebElement> cartProductsElements, String productName){
		boolean isAvailable = cartProductsElements.stream().anyMatch(product -> product.getText().contains(productName));
		return isAvailable;
	}
}
