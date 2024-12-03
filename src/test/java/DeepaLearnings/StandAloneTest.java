package DeepaLearnings;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class StandAloneTest {
	public WebDriver driver;

	@Test
	public void MyTest() {

		driver = new ChromeDriver();
		String productName = "ZARA COAT 3";

		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();

		driver.findElement(By.xpath("/html//input[@id='userEmail']")).sendKeys("anshika@gmail.com");
		driver.findElement(By.xpath("/html//input[@id='userPassword']")).sendKeys("Iamking@000");
		driver.findElement(By.xpath("/html//input[@id='login']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		 wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//app-root/app-profile[@class='ng-star-inserted']/div/div[@class='cart']/ul[@class='cartWrap ng-star-inserted']//button[@class='btn btn-primary']")).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.xpath(
				"//app-root/app-order[@class='ng-star-inserted']/section[@class='content']/div[@class='container-fluid']/div[@class='row']/div[@class='col-md-7']/div[@class='container']//a"))
				.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String confirmMessage = driver.findElement(By.xpath(
				"//table[@id='htmlData']//td[@class='box']/table/tbody/tr/td/table/tbody/tr[1]/td/h1[@class='hero-primary']"))
				.getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();

	}
}
