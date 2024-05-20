package com.rbhatt.selenium.StepDefinitions;

import com.rbhatt.selenium.PageObjects.*;
import com.rbhatt.selenium.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionsImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalouge productCatalouge;
	public CartPage cartPage;
	public PlaceOrder placeOrder;
	public OrdersHistory ordersHistory;
	String orderID;
	
	@Given("I landed on the Ecommerce Page")
	public void I_landed_on_the_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^I Logged in with email (.+) and password (.+)$")
	public void I_Logged_in_with_email_and_password(String email, String password){
		productCatalouge = landingPage.loginAction(email, password);
	}
	
	@When("^I add product (.+) to the Cart$")
	public void I_add_product_to_the_Cart(String product){
		List<WebElement> productNames = productCatalouge.getProductList();
		WebElement prod = productCatalouge.getProductByName(product);
		productCatalouge.addToCart(prod);
	}
	
	@And("^Checkout (.+) and submit the Order$")
	public void Checkout_Product_and_submit_the_Order(String product){
		cartPage = productCatalouge.goToCart();
		Boolean isAvailable = cartPage.isProductAvailable(product);
		Assert.assertTrue(isAvailable);
		placeOrder = cartPage.checkout();
		placeOrder.placeOrder();
	}
	
	@Then("^(.+) message is displayed on Confirmation Page$")
	public void Confirmation_message_is_displayed_on_Confirmation_Page(String confirmationMessage){
		String confirmMessage = placeOrder.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.contains(confirmationMessage));
		orderID = placeOrder.getOrderID();
	}
	
	@And("I should see the Order on Order History Page")
	public void I_should_see_the_Order_on_Order_History_Page(){
		ordersHistory = productCatalouge.goToOrderHistory();
		Boolean isOrderAvailable = ordersHistory.isOrderAvailable(orderID);
		Assert.assertTrue(isOrderAvailable);
		driver.quit();
	}
}
