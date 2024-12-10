package DeepaLearnings.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductCatalogPage extends BasePage {

	private By listOfProduct = By.cssSelector(".mb-3");
	private By singleProduct = By.cssSelector("b");
	private By addToCartButton = By.cssSelector(".card-body button:last-of-type");
	private By confirmMsg = By.cssSelector("#toast-container");
	private By spinner = By.cssSelector(".ng-animating");

	private List<WebElement> products;

	public ProductCatalogPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public OrderPage addProductToCart(String prod) {

		waitForElementToAppear(listOfProduct);
		products = listofWebelements(listOfProduct);
		WebElement product = getProductByName(prod);

		product.findElement(addToCartButton).click();
		// click(addToCartButton);
		waitForElementToAppear(confirmMsg);
		waitForElementToDisappear(spinner);
		HeaderPage headerPg = new HeaderPage(driver);
		OrderPage orderPg = headerPg.clickOnCart();
		return orderPg;
	}

	public CheckOrderPage checkOrder() {
		HeaderPage headerPage = new HeaderPage(driver);
		return headerPage.clickOnOrder();

	}
}
