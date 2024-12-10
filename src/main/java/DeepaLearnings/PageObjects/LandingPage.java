package DeepaLearnings.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {

	private By emailId = By.xpath("/html//input[@id='userEmail']");
	private By password = By.xpath("/html//input[@id='userPassword']");
	private By loginButton = By.xpath("/html//input[@id='login']");
	private By errorMessage = By.cssSelector("[class*='flyInOut']");
	private String url = "https://rahulshettyacademy.com/client";

	public LandingPage(WebDriver driver) {
		super(driver);
	}

	public ProductCatalogPage launchInitialPage(String id, String pwd) {
		goTo(url);
		type(emailId, id);
		type(password, pwd);
		click(loginButton);
		return new ProductCatalogPage(driver);

	}

	public String getErrorMessage() {
		waitForElementToAppear(errorMessage);
		String message = find(errorMessage).getText();
		return message;
	}
}
