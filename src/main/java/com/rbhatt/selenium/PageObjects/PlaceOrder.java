package com.rbhatt.selenium.PageObjects;

import com.rbhatt.selenium.AbstractCompoments.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PlaceOrder extends AbstractComponent {

    By countriesEle = By.xpath("//div[@class='form-group']/section");

    @FindBy(xpath = "//div/input[@placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "//div[@class='form-group']/section/button")
    List<WebElement> countries;

    @FindBy(xpath = "//div[@class='actions']/a")
    WebElement placeOrder;

    @FindBy(css = ".hero-primary")
    WebElement confirmationMessageEle;

    WebDriver driver;
    public PlaceOrder(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void placeOrder(){
        country.sendKeys("Ind");
        waitForElementToAppear(countriesEle);
        for(WebElement country : countries){
            if(country.getText().equalsIgnoreCase("Indonesia")){
                country.click();
                break;
            }
        }
        placeOrder.click();
    }

    public String getConfirmationMessage(){
        return confirmationMessageEle.getText();
    }
}
