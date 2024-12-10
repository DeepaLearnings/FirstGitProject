package DeepaLearnings.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import DeepaLearnings.GeneralClasses.Base;
import DeepaLearnings.GeneralClasses.Retry;
import DeepaLearnings.PageObjects.LandingPage;
import DeepaLearnings.PageObjects.OrderPage;
import DeepaLearnings.PageObjects.ProductCatalogPage;

public class ErrorValidationTest extends Base {

	@Test(dataProvider = "LoginValidationTest_Data", groups = "ErrorValidations",retryAnalyzer =Retry.class)
	public void LoginValidationTest(HashMap<String, String> inputData) {

		LandingPage landingPage = new LandingPage(driver);
		landingPage.launchInitialPage(inputData.get("email"), inputData.get("password"));
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test(dataProvider = "ProductValidationTest_Data", groups = "ErrorValidations")
	public void ProductValidationTest(String id, String pwd, String correctproduct, String inCorrectproduct) {

		LandingPage landingPage = new LandingPage(driver);
		ProductCatalogPage productCatalogPage = landingPage.launchInitialPage(id, pwd);
		OrderPage orderPage = productCatalogPage.addProductToCart(correctproduct);
		boolean Status = orderPage.checkProduct(inCorrectproduct);

		Assert.assertFalse(Status);

	}

	@DataProvider
	public Object[][] LoginValidationTest_Data() throws IOException

	{

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "Hemansh@gmail.com");
		map.put("password","Kavya@123");
		return new Object[][] { { map } };

	}

	@DataProvider
	public Object[][] ProductValidationTest_Data() throws IOException

	{
		return new Object[][] { { "Hemansh@gmail.com", "Kavya@1234", "ZARA COAT 3", "ZARA COAT 3" } };

	}

}
