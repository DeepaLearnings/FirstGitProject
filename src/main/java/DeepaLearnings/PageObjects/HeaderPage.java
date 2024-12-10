package DeepaLearnings.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage extends BasePage {

	private By cart = By.cssSelector("[routerlink*='cart']");
	private By order = By.cssSelector("li:nth-of-type(3) > .btn.btn-custom");

	public HeaderPage(WebDriver driver) {
		super(driver);
	}

	public OrderPage clickOnCart() {

		waitForElementToAppear(cart);
		click(cart);
		return new OrderPage(driver);
	}

	public CheckOrderPage clickOnOrder() {

		waitForElementToAppear(order);
		click(order);
		return new CheckOrderPage(driver);
	}
}
