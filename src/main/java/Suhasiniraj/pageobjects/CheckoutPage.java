package Suhasiniraj.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Suhasiniraj.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;


	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectCountry;

	@FindBy(css = ".action__submit")
	private WebElement submit;

	@FindBy(css = "[placeholder='Select Country']")
	private WebElement country;

	private By results = By.cssSelector(".ta-results");
	

	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
	}
		public ConfirmationPage submitOrder()
		{
			submit.click();
			ConfirmationPage confirm=new ConfirmationPage(driver);
			return confirm;
		}

	

}
