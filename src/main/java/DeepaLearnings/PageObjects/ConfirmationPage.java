package DeepaLearnings.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ConfirmationPage extends BasePage {

	private By message = By.xpath(
			"//table[@id='htmlData']//td[@class='box']/table/tbody/tr/td/table/tbody/tr[1]/td/h1[@class='hero-primary']");

	public ConfirmationPage(WebDriver driver) {
		super(driver);

	}

	public String getMessage() {
		String actualMessage = find(message).getText();
		return actualMessage;
	}
}
