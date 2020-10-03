package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003_Error_Verification extends BaseClass {

	
	@Test
	public void addToCart() throws InterruptedException
	{
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@title=\"Xperia\"]//following::button[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@title=\"Qty\"]")).clear();
		driver.findElement(By.xpath("//input[@title=\"Qty\"]")).sendKeys("1000");
		driver.findElement(By.xpath("//span[text()=\"Update\"]")).click();
		String updateCartError = driver.findElement(By.xpath("//p[@class=\"item-msg error\"]")).getText();
		System.out.println(updateCartError);
		if(updateCartError.trim().equals("* The maximum quantity allowed for purchase is 500."))
		{
			Assert.assertTrue(true);
		}else
		{
			captureScreenshot(driver, "Error message- \"Maximum allowed quantity\" Heading of compare product window");
			Assert.assertTrue(false);
		}	
		driver.findElement(By.xpath("//span[text()=\"Empty Cart\"]")).click();
		String emtpyCartErr = driver.findElement(By.xpath("//div[@class=\"page-title\"]")).getText();
		System.out.println(emtpyCartErr);
		if(emtpyCartErr.equals("SHOPPING CART IS EMPTY"))
		{
			Assert.assertTrue(true);
		}else
		{
			captureScreenshot(driver, "Error message- \"Shopping cart is empty\" is not displayed");
			Assert.assertTrue(false);
		}			
	}

}
