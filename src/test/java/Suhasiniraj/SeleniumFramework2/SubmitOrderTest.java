package Suhasiniraj.SeleniumFramework2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Suhasiniraj.TestComponents.BaseTest;
import Suhasiniraj.pageobjects.CartPage;
import Suhasiniraj.pageobjects.CheckoutPage;
import Suhasiniraj.pageobjects.ConfirmationPage;
import Suhasiniraj.pageobjects.Landingpage;
import Suhasiniraj.pageobjects.OrderPage;
import Suhasiniraj.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;


public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		ProductCatalogue productCata=landingpage.loginApplication(input.get("email"),input.get("Password"));
		List<WebElement>products=productCata.getProductList();
		productCata.addProductToCart(input.get("productName"));
		//productCata.getProductByName(productName);
		CartPage cartpage = (CartPage) productCata.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage page2 = (CheckoutPage) cartpage.goToCheckout();
		page2.selectCountry("india");
		ConfirmationPage confirm = (ConfirmationPage) page2.submitOrder();
		String confirmationMessage=confirm.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();

	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		//"ZARA COAT 3";
		ProductCatalogue productCata = landingpage.loginApplication("suhasini@gmail.com", "Chinnubaby123!!");
		OrderPage order = productCata.goToOrdersPage();
		Assert.assertTrue(order.VerifyOrderDisplay(productName));
}
	
	
	@DataProvider
	public  Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Suhasiniraj\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}

//public Object[][] getData()
//{
//  return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };
//  
//}

//HashMap<String, String> map=new HashMap<String,String>();
//map.put("email", "suhasini@gmail.com");
//map.put("Password", "Chinnubaby123!!");
//map.put("productName", "ZARA COAT 3");
//
//HashMap<String, String> map1=new HashMap<String,String>();
//map1.put("email", "suhasraj@gmail.com");
//map1.put("Password", "Chinnubaby123!!");
//map1.put("productName", "ADIDAS ORIGINAL");
//return new Object[][] {{map},{map1}};

