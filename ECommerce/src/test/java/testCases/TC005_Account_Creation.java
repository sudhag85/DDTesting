package testCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import utility.StringGeneration;

public class TC005_Account_Creation extends BaseClass{

	@Test
	public void accountRegistration() throws InterruptedException
	{
		driver.findElement(By.xpath("//div[@class='block-title']//following::a[8]")).click();
		Thread.sleep(6000);
		closePlayer();
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("firstname")).clear();
		driver.findElement(By.id("firstname")).sendKeys("Pandian");

		driver.findElement(By.id("lastname")).clear();
		driver.findElement(By.id("lastname")).sendKeys("Anand");

		driver.findElement(By.id("email_address")).clear();
		driver.findElement(By.id("email_address")).sendKeys("Pandian@gmail.com");
		/*String email= StringGeneration.generateString();
		System.out.println(email);
		driver.findElement(By.id("email_address")).sendKeys(email+"@gmail.com");*/

		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("Pandian123");

		driver.findElement(By.id("confirmation")).clear();
		driver.findElement(By.id("confirmation")).sendKeys("Pandian123");

		driver.findElement(By.xpath("//span[text()='Register']")).click();
		Thread.sleep(2000);
		String RegistrationStatus = driver.findElement(By.xpath("//li[@class='success-msg']//child::span")).getText();
		System.out.println(RegistrationStatus);
		Assert.assertEquals(RegistrationStatus, "Thank you for registering with Main Website Store.");
	}

	@Test
	public void shareWishList()
	{
		driver.findElement(By.xpath("//a[text()='TV']")).click();
		driver.findElement(By.xpath("//*[text()='LG LCD']//ancestor::li[1]//*[text()='Add to Wishlist']")).click();
		driver.findElement(By.xpath("//button[@name='save_and_share']")).click();
		driver.findElement(By.id("email_address")).clear();
		driver.findElement(By.id("email_address")).sendKeys("sudhasathy@gmail.com");
		driver.findElement(By.xpath("//button//span[text()='Share Wishlist' ]")).click();
		String wishlistStatus = driver.findElement(By.xpath("//li[@class='success-msg' ]//span")).getText();
		System.out.println(wishlistStatus);
		Assert.assertEquals(wishlistStatus, "Your Wishlist has been shared.");
		
		driver.findElement(By.xpath("//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
	}
	
	
	
}
