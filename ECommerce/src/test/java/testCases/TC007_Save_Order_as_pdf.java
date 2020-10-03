package testCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC007_Save_Order_as_pdf extends BaseClass
{
	long expectedOrderNo = 100011812;
	String expectedStatus = "Pending";
	@Test(priority=1)
	public void login()
	{
		driver.findElement(By.xpath("//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log In']")).click();

		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("Lilly@gmail.com");

		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("Lilly123");

		driver.findElement(By.id("send2")).click();

	}

	@Test(priority=2)
	public void viewOrder() throws InterruptedException
	{
		Thread.sleep(7000);
		closePlayer();
		driver.findElement(By.xpath("//a[text()='My Orders']")).click();

		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='my-orders-table']//tr"));
		int totalRows = rows.size();

		List<WebElement> columns = driver.findElements(By.xpath("//table[@id='my-orders-table']//td"));
		int totalCols = columns.size();

		for(int i = 1; i<totalRows; i++)
		{
			int actualOrderNo = Integer.parseInt(driver.findElement(By.xpath("//table[@id='my-orders-table']//tr["+i+"]//td[1]")).getText());
			String actualStatus = driver.findElement(By.xpath("//table[@id='my-orders-table']//tr["+i+"]//td[5]")).getText();
			try {
				if((actualOrderNo==expectedOrderNo)&&(actualStatus.equals(expectedStatus)))
				{
					Assert.assertTrue(true);
					break;
				}
			}
			catch(Exception e)
			{
				captureScreenshot(driver,"viewOrder");
				System.out.println(e.getMessage());
			}
		}
	}

	@Test(priority=3)
	public void printOrder()
	{
		driver.findElement(By.xpath("//td[@class ='a-center view last']//a[text()='View Order']")).click();
		driver.findElement(By.className("link-print")).click();
		
	}
	
}
