package DeepaLearnings.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckOutPage extends BasePage {

	private By countryBox = By.cssSelector("[placeholder='Select Country']");
	private By dropDown = By.cssSelector(".ta-results");
	private By selectCountry = By.xpath("(//button[contains(@class,'ta-item')])[2]");
	private By placeOrderButton = By.cssSelector(".action__submit");

	public CheckOutPage(WebDriver driver) {
		super(driver);
	}

	public ConfirmationPage placeOrder(String country) {
		Actions action = new Actions(driver);
		action.sendKeys(find(countryBox), country).build().perform();
		waitForElementToAppear(dropDown);
		click(selectCountry);
		return clickOnPlaceOrderButton();

	}

	public ConfirmationPage clickOnPlaceOrderButton() {

		click(placeOrderButton);
		return new ConfirmationPage(driver);
	}
}
