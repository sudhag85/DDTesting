package testCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC009_Discount_Coupon extends BaseClass {
	
	@Test
	public void discountCoupon() throws InterruptedException
	{
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		Thread.sleep(5000);
		closePlayer();
		List<WebElement> products = driver.findElements
				(By.xpath("//ul[@class='products-grid products-grid--max-4-col first last odd']//h2/a"));
		for (WebElement webElement : products) {
			if(webElement.getText().equals("IPHONE"))
			{
				driver.findElement(By.xpath("//h2/a[@title = \"IPhone\"]//following::div[3]/button")).click();
				Thread.sleep(5000);
				closePlayer();
				break;
			}
		}
		
		driver.findElement(By.id("coupon_code")).clear();
		driver.findElement(By.id("coupon_code")).sendKeys("GURU50");
		driver.findElement(By.xpath("//*[@class='button-wrapper']/button[@title='Apply']")).click();
		
		Select country = new Select(driver.findElement(By.id("country")));
		country.selectByVisibleText("United States");
		
		Select state = new Select(driver.findElement(By.id("region_id")));
		state.selectByVisibleText("New York");
		
		driver.findElement(By.id("postcode")).sendKeys("542896");
		
		driver.findElement(By.xpath("//button[@title='Estimate']")).click();
		
		
		
		String sTotal = driver.findElement(By.xpath("//*[@id='shopping-cart-totals-table']//tbody//tr[1]/td[2]/span")).getText();
		double subTotal = Double.parseDouble(sTotal.replaceAll("[$,]", ""));
		
		String discount = driver.findElement(By.xpath("//*[@id='shopping-cart-totals-table']//tbody//tr[2]/td[2]/span")).getText();
		double totalDiscount = Double.parseDouble(discount.replaceAll("[$,]", ""));
		
		String gTotal = driver.findElement(By.xpath("//*[@id='shopping-cart-totals-table']//tfoot/tr/td[2]")).getText();
		double grandTotal = Double.parseDouble(gTotal.replaceAll("[$,]", ""));
		
		if((subTotal-totalDiscount)==grandTotal)
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
}
