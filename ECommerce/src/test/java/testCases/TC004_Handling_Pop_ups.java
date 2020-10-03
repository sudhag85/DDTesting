package testCases;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC004_Handling_Pop_ups extends BaseClass {

	@Test
	public void compareTwoProducts() throws InterruptedException {

		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		Thread.sleep(3000);
		String parentWindow = driver.getWindowHandle();
		try {
			if(driver.findElement(By.xpath("//*[@class=\"block block-list block-compare\"]")).isDisplayed())
			{		
				List<WebElement> itemsInCompareList = driver.findElements(By.xpath("//*[@id=\"compare-items\"]//child::li"));
				int totalItems = itemsInCompareList.size();

				for (WebElement webElement : itemsInCompareList) {
					driver.findElement(By.xpath("//*[@class=\"btn-remove\"]")).click();
					driver.switchTo().alert().accept();
					driver.switchTo().defaultContent();
				}
			}
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		driver.findElement(By.xpath("//*[@title='Xperia']//following::a[4]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@title='IPhone']//following::a[4]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Compare']")).click();
		Thread.sleep(3000);
		if(isWindowPresent())
		{
			String heading = driver.findElement(By.xpath("//div[@class='page-title title-buttons']//following::h1")).getText();
			System.out.println("Heading of the child window is: "+heading);
			if(heading.equals("COMPARE PRODUCTS"))
			{
				Assert.assertTrue(true);
			}else
			{
				captureScreenshot(driver, "compareTwoProducts");
				Assert.assertTrue(false);
			}
			
			String product1 = driver.findElement(By.xpath("//h2/a[@title=\"Sony Xperia\"]")).getText();
			Assert.assertEquals(product1, "SONY XPERIA");

			String product2 = driver.findElement(By.xpath("//h2/a[@title=\"IPhone\"]")).getText();
			Assert.assertEquals(product2, "IPHONE");
						
			driver.close();
			
			Set<String> handles = driver.getWindowHandles();
			for (String string : handles) {
				if(string.equals(parentWindow)) {
					Assert.assertTrue(true);
					driver.switchTo().window(string);
				}else
				{
					Assert.assertTrue(false);
				}
				
			}
		}
	}

	boolean isWindowPresent()
	{
		try {
			Set<String> childWindows = driver.getWindowHandles();
			for (String newWindowHandle : childWindows) 
			{
				driver.switchTo().window(newWindowHandle);
			}
			return true;
		}
		catch(Exception e)
		{
			captureScreenshot(driver, "Compare products window is not opened");
			System.out.println(e.getMessage());
			return false;
		}
	}

}
