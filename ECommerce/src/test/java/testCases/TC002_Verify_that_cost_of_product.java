package testCases;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TC002_Verify_that_cost_of_product extends BaseClass  {
	
	@Test
	public void verifyProductPrice() throws InterruptedException
	{
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		Thread.sleep(3000);
		String listPagePrice = driver.findElement(By.xpath("//*[text()=\"Sony Xperia\"]//following::span[2]")).getText();
		System.out.println("Price of Sony Experia in list page is: " + listPagePrice);
		driver.findElement(By.xpath("//*[@title=\"Xperia\"]")).click();
		Thread.sleep(3000);
		String detailPagePrice = driver.findElement(By.xpath("//*[@id=\"product-price-1\"]//child::span")).getText();
		System.out.println("Price of Sony Experia in detail page is: " + detailPagePrice);
		if(listPagePrice.equals(detailPagePrice))
		{
			Assert.assertTrue(true);
		}else
		{
			captureScreenshot(driver, "Price of lust page and detail page are not equal");
			Assert.assertTrue(false);
		}

}
}
