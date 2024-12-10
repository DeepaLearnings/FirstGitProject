package DeepaLearnings.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import DeepaLearnings.GeneralClasses.Base;
import DeepaLearnings.PageObjects.CheckOrderPage;
import DeepaLearnings.PageObjects.CheckOutPage;
import DeepaLearnings.PageObjects.ConfirmationPage;
import DeepaLearnings.PageObjects.LandingPage;
import DeepaLearnings.PageObjects.OrderPage;
import DeepaLearnings.PageObjects.ProductCatalogPage;

public class SubmitOrderTest extends Base {

	@Test(dataProvider = "getData", groups = "Purchase")
	public void submitOrder(HashMap<String, String> inputData) {

		LandingPage landingPage = new LandingPage(driver);
		ProductCatalogPage productCatalogPage = landingPage.launchInitialPage(inputData.get("id"),
				inputData.get("pwd"));
		OrderPage orderPage = productCatalogPage.addProductToCart(inputData.get("product"));
		boolean Status = orderPage.checkProduct(inputData.get("product"));

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(Status);

		CheckOutPage checkOutPage = orderPage.checkOut();
		ConfirmationPage confirmationPage = checkOutPage.placeOrder(prop.getProperty("country"));
		String actualMessage = confirmationPage.getMessage();

		softAssert.assertTrue(actualMessage.equalsIgnoreCase(prop.getProperty("expectedMessage")));
		softAssert.assertAll();
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void checkOrders() {
		LandingPage landingPage = new LandingPage(driver);
		ProductCatalogPage productCatalogPage = landingPage.launchInitialPage("Hemansh@gmail.com", "Kavya@1234");
		CheckOrderPage checkOrderPage= productCatalogPage.checkOrder();
		Assert.assertTrue(checkOrderPage.verifyProduct("ZARA COAT 3"));
		
	}

	@DataProvider
	public Object[][] getData() throws IOException

	{

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//resources//PurchaseOrder.Json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
