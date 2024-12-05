package com.rbhatt.selenium.AbstractCompoments;

import com.rbhatt.selenium.PageObjects.CartPage;
import com.rbhatt.selenium.PageObjects.OrdersHistory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent{
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement goToCartBtn;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement goToOrdersHistoryBtn;
	
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	// Waits for an element located by the specified By locator to become visible.
	public void waitForElementToAppear(By findBy){
		WebDriverWait waitForElementAppear = new WebDriverWait(driver, Duration.ofSeconds(10));
		waitForElementAppear.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	// Waits for the given WebElement to become visible on the page.
	public void waitForWebElementToVisible(WebElement findBy){
		WebDriverWait waitForWebElementAppear = new WebDriverWait(driver, Duration.ofSeconds(10));
		waitForWebElementAppear.until(ExpectedConditions.visibilityOf(findBy));
	}

	// Waits for an element located by the specified By locator to disappear or become invisible.
	public void waitForElementToDisappear(By findBy){
		WebDriverWait waitForElementDisappear = new WebDriverWait(driver, Duration.ofSeconds(10));
		waitForElementDisappear.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}

	// Clicks on the specified WebElement after ensuring it is clickable.
	public void click(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element)); // Ensure element is clickable
		element.click(); // Perform the click action
	}

	// Sends text to the specified WebElement after ensuring it is visible.
	public void sendKeys(WebElement element, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element)); // Ensure element is visible
		element.clear(); // Clear any existing text
		element.sendKeys(text); // Send the input text
	}

	// Scrolls the page to bring the specified WebElement into view.
	public void scrollToElement(WebElement element) {
        try {
            // Scroll the element into view
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);

            // Additional scrolling to adjust for fixed headers or footers
            int yOffset = element.getRect().getY();
            js.executeScript("window.scrollBy(0, -50);"); // Adjust by 50px for headers
        } catch (Exception e) {
            throw new RuntimeException("Failed to scroll to the element: " + element, e);
        }
    }

	// Retrieves the visible text from the specified WebElement.
	public String getText(WebElement element) {
		waitForWebElementToVisible(element);
		return element.getText();
	}

	/**
	 * Performs a mouse hover action on the specified WebElement.
	 *
	 * @param element The WebElement to hover over.
	 */
	public void mouseHover(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	/**
	 * Selects an option in a dropdown menu by its visible text.
	 *
	 * @param element The dropdown WebElement.
	 * @param text    The visible text of the option to select.
	 */
	public void selectDropdownByVisibleText(WebElement element, String text) {
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(text);
	}
	
	public CartPage goToCart(){
		goToCartBtn.click();
		return new CartPage(driver);
	}
	
	public OrdersHistory goToOrderHistory(){
		goToOrdersHistoryBtn.click();
		return new OrdersHistory(driver);
	}
}
