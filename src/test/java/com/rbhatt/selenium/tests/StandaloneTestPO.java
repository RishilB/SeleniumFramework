package com.rbhatt.selenium.tests;

import com.rbhatt.selenium.PageObjects.*;
import com.rbhatt.selenium.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class StandaloneTestPO extends BaseTest {
    
    String orderID;
    
    @Test(dataProvider = "getData",groups = {"PurchaseOrder"})
    public void submitOrder(HashMap<String,String> input) throws IOException {

        //String productName = "ZARA COAT 3";

        //1. Perform Login
        ProductCatalouge productCatalouge = landingPage.loginAction(input.get("email"), input.get("password"));
        
        //2. Get all Product Names
        List<WebElement> productNames = productCatalouge.getProductList();
        WebElement prod = productCatalouge.getProductByName(input.get("product"));
        productCatalouge.addToCart(prod);
        
        //3. Apply explicit wait for the Toast visibility & click on Cart Link from header
        CartPage cartPage = productCatalouge.goToCart();
        
        //4. Check the Added Product is there in the Cart listing page
        Boolean isAvailable = cartPage.isProductAvailable(input.get("product"));
        Assert.assertTrue(isAvailable);
        PlaceOrder placeOrder = cartPage.checkout();
        
        //5. Place Order
        placeOrder.placeOrder();
        
        //6. Assert Confirmation Message
        String confirmMessage = placeOrder.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.contains("THANKYOU"));
        
        //7. Get Order ID
        orderID = placeOrder.getOrderID();
    }
    
    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryPage(){
        //1. Perform Login
        landingPage.loginAction("risshilbhatt@gmail.com", "Test@123");
        // Go to Order History Page
        OrdersHistory ordersHistory = landingPage.goToOrderHistory();
        Boolean isOrderAvailable = ordersHistory.isOrderAvailable(orderID);
        Assert.assertTrue(isOrderAvailable);
    }
    
    @DataProvider
    public Object[][] getData(){
        HashMap<String,String> map1 = new HashMap<String,String>();
        map1.put("email","risshilbhatt@gmail.com");
        map1.put("password","Test@123");
        map1.put("product","ZARA COAT 3");
        HashMap<String,String> map2 = new HashMap<String,String>();
        map2.put("email","anishka@gmail.com");
        map2.put("password","Iamking@000");
        map2.put("product","ADIDAS ORIGINAL");
        return new Object[][] {{map1},{map2}};
    }
}
