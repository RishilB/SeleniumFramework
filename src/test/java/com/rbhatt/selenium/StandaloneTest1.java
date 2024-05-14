package com.rbhatt.selenium;

import com.rbhatt.selenium.AbstractCompoments.AbstractComponent;
import com.rbhatt.selenium.PageObjects.CartPage;
import com.rbhatt.selenium.PageObjects.LandingPage;
import com.rbhatt.selenium.PageObjects.PlaceOrder;
import com.rbhatt.selenium.PageObjects.ProductCatalouge;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandaloneTest1 {
    public static void main(String[] args){

        String productName = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        //1. Perform Login
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo("https://rahulshettyacademy.com/client");
        ProductCatalouge productCatalouge = landingPage.loginAction("risshilbhatt@gmail.com", "Test@123");
        
        //2. Get all Product Names
        List<WebElement> productNames = productCatalouge.getProductList();
        WebElement prod = productCatalouge.getProductByName(productName);
        productCatalouge.addToCart(prod);
        
        //3. Apply explicit wait for the Toast visibility & click on Cart Link from header
        CartPage cartPage = productCatalouge.goToCart();
        
        //4. Check the Added Product is there in the Cart listing page
        Boolean isAvailable = cartPage.isProductAvailable(productName);
        Assert.assertTrue(isAvailable);
        PlaceOrder placeOrder = cartPage.checkout();
        
        //5. Place Order
        placeOrder.placeOrder();
        
        //6. Assert Confirmation Message
        String confirmMessage = placeOrder.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.contains("THANKYOU"));
        driver.quit();
    }
}
