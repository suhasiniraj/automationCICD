package Suhasiniraj.SeleniumFramework2;

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

import Suhasiniraj.pageobjects.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();   //launching browser
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));    //implicit wait
		driver.manage().window().maximize();									// maximize the screen
		
		driver.get("https://rahulshettyacademy.com/client/");     //open website 
		
		Landingpage landingpage = new Landingpage(driver);
		
		//login using username, password and submit - landing page
		driver.findElement(By.id("userEmail")).sendKeys("suhasini@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Chinnubaby123!!");
		driver.findElement(By.id("login")).click();
		
		//explicit wait
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));  //store all the products in list 
		
		//for & if loop using java streams to check if the item given is matching then add to cart
		WebElement prod =	products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();     //click on add to cart button
	
		//toast message to appeared - "product Added to cart" - explicit wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));       //toast to appear
		//ng-animating - class name
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));  //toast to disappear
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();      //click on cart button
		
		//step to get all the items from cart and check if it has matching product
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));		
		Boolean match = 	cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();     // click on checkout button
		
		//action class to select and enter country name "india"
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results"))); //from the row of countries
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click(); // select place where india is present
		
		driver.findElement(By.cssSelector(".action__submit")).click();    //click on place order button
		
		//scan the text after placing order and assert the text 
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
	}

}
