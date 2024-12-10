package DeepaLearnings.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckOrderPage extends BasePage {

	private By orderedProductsList = By.cssSelector("tr td:nth-child(3)");

	public CheckOrderPage(WebDriver driver) {
		super(driver);
	}

	public boolean verifyProduct(String prod){
		boolean result;
		List<WebElement> orderedList = listofWebelements(orderedProductsList);
		result=orderedList.stream().anyMatch(product->product.getText().equalsIgnoreCase(prod));
		return result;
	}
}


