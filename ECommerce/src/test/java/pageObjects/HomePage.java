package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public WebDriver driver;
	
	public HomePage(WebDriver r)
	{
		driver = r;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Mobile")
	WebElement lnkMobile;
	
	public void clkMobile()
	{
		lnkMobile.click();
	}
}
