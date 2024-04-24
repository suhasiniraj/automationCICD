package Suhasiniraj.SeleniumFramework2;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Suhasiniraj.TestComponents.BaseTest;
import Suhasiniraj.TestComponents.Retry;
import Suhasiniraj.pageobjects.CartPage;
import Suhasiniraj.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void submitOrder() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCata = landingpage.loginApplication("suhasini@gmail.com", "Chinnubaby123!!");
		Assert.assertEquals("Login Successfully", landingpage.getErrorMessage());

	}

	@Test(retryAnalyzer = Retry.class)
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCata = landingpage.loginApplication("suhasini@gmail.com", "Chinnubaby");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
		List<WebElement> products = productCata.getProductList();
		CartPage cartpage = (CartPage) productCata.goToCartPage();
		productCata.addProductToCart(productName);
		Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

	}
}
