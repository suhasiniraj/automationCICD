package Suhasiniraj.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Suhasiniraj.AbstractComponents.AbstractComponent;

public class Landingpage extends AbstractComponent{

		WebDriver driver;
		
		public Landingpage(WebDriver driver)
		{
			super(driver);
			//initialization
			this.driver=driver;
			PageFactory.initElements(driver, this);  //to initialize and construct the driver in page factory 
			
		}
		
		//WebElement userEmail= driver.findElement(By.id("userEmail"));
		//WebElement userPassword= driver.findElement(By.id("userPassword"));
		//WebElement Submit= driver.findElement(By.id("login"));
		
		//Pagefactory
		@FindBy(id="userEmail")
		WebElement userEmail;
		
		@FindBy(id="userPassword")
		WebElement passwordEle;
		
		@FindBy(id="login")
		WebElement Submit;
		
		@FindBy(css="[class*='flyInOut']")
		WebElement errorMessage;
		
		//actions - sendkeys 
		public ProductCatalogue loginApplication(String email,String password)
		{
			userEmail.sendKeys(email);
			passwordEle.sendKeys(password);
			Submit.click();
			ProductCatalogue productCata= new ProductCatalogue(driver);
			return productCata;
		}
		public String getErrorMessage()
		{
			waitForWebElementToAppear(errorMessage);
			return errorMessage.getText();
		}
		
		public void goTo()
		{
			driver.get("https://rahulshettyacademy.com/client/"); 
		}
		
	}


