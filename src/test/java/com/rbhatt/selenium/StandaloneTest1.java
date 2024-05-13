package com.rbhatt.selenium;

import com.rbhatt.selenium.AbstractCompoments.AbstractComponent;
import com.rbhatt.selenium.PageObjects.LandingPage;
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

        // 1. Perform Login
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo("https://rahulshettyacademy.com/client");
        landingPage.loginAction("risshilbhatt@gmail.com", "Test@123");
        //2. Get all Product Names
        ProductCatalouge productCatalouge = new ProductCatalouge(driver);
        List<WebElement> productNames = productCatalouge.getProductList();
        WebElement prod = productCatalouge.getProductByName(productName);
        productCatalouge.addToCart(prod);
        //3. Apply explicit wait for the Toast visibility & click on Cart Link from header
        productCatalouge.goToCart();
        
        
        

        //4. Check the Added Product is there in the Cart listing page
        List<WebElement> cartProductsElements = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
        boolean isAvailable = cartProductsElements.stream().anyMatch(product -> product.getText().contains(productName));
        Assert.assertTrue(isAvailable);
        driver.findElement(By.cssSelector("li[class='totalRow'] button[type='button']")).click();
        
        //5. Place Order
        driver.findElement(By.xpath("//div/input[@placeholder='Select Country']")).sendKeys("Ind");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='form-group']/section"))));
        List<WebElement> countries = driver.findElements(By.xpath("//div[@class='form-group']/section/button"));
        for(WebElement country : countries){
            if(country.getText().equalsIgnoreCase("Indonesia")){
                country.click();
                break;
            }
        }
        driver.findElement(By.xpath("//div[@class='actions']/a")).click();
        
        //6. Assert Confirmation Message
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        System.out.println(confirmMessage);
        Assert.assertTrue(confirmMessage.contains("THANKYOU"));
        driver.quit();
    }
}
