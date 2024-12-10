package DeepaLearnings.PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public void goTo(String url) {
		driver.get(url);
	}

	public void type(By locator, String text) {
		waitForElementToAppear(locator);
		find(locator).sendKeys(text);
	}

	protected WebElement find(By locator) {
		return driver.findElement(locator);
	}

	protected void click(By locator) {
		waitForElementToAppear(locator);
		find(locator).click();
	}

	protected List<WebElement> listofWebelements(By locator) {
		List<WebElement> products = driver.findElements(locator);
		return products;
	}

	public void waitForElementToAppear(By locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public void waitForWebElementToAppear(WebElement webElement) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(webElement));

	}

	public void waitForElementToDisappear(By locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

	}

}
