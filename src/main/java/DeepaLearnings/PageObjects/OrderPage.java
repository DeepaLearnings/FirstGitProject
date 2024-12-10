package DeepaLearnings.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage extends BasePage {

	private By cardProducts = By.cssSelector(".cartSection h3");
	private By checkOutButton = By.cssSelector(".totalRow button");
	public OrderPage(WebDriver driver) {
		super(driver);

	}

	public Boolean checkProduct(String prod) {
		Boolean result;
		waitForElementToAppear(cardProducts);
		List<WebElement> cartProducts = listofWebelements(cardProducts);
		result = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(prod));
		return result;
	}

	public CheckOutPage checkOut() {
		click(checkOutButton);
		return new CheckOutPage(driver);
	}
}
