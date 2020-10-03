package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC006_Purchase_Product extends BaseClass {

	String ShipCost;

	@Test(priority = 1)
	public void login()
	{
		driver.findElement(By.xpath("//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log In']")).click();

		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("Pandian@gmail.com");

		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("Pandian123");

		driver.findElement(By.id("send2")).click();

	}

	@Test(priority = 2)
	public void addToCart() throws InterruptedException
	{
		Thread.sleep(7000);
		closePlayer();
		driver.findElement(By.xpath("//a[text()='My Wishlist']")).click();
		driver.findElement(By.xpath("//div[@class=\"cart-cell\"]/button[@title=\"Add to Cart\"]")).click();
		String Total = driver.findElement(By.xpath("//td[@class='product-cart-total']//child::span//span")).getText();
		Total = Total.replace("$", "");
		String subTotal = Total.trim();
		System.out.println("Price of the selected item: " + subTotal);
		/*string pattern = @"\d\.?";
        Regex rgx = new Regex(pattern);
        string sentence = @"$99,000.00";
        string value = "";

        foreach (Match match in rgx.Matches(sentence))
        {
            value = value+match.Value;
        }*/
		
		
		

		
		Select country = new Select(driver.findElement(By.id("country")));
		country.selectByVisibleText("United States");
		
		Select state = new Select(driver.findElement(By.id("region_id")));
		state.selectByVisibleText("New York");
		
		driver.findElement(By.id("postcode")).sendKeys("542896");
		
		driver.findElement(By.xpath("//button[@title='Estimate']")).click();

		try 
		{
			String shippingCost = driver.findElement(By.xpath("//label[@for=\"s_method_flatrate_flatrate\"]/span")).getText();
			shippingCost = shippingCost.replace("$", "");
			ShipCost = shippingCost.trim();
			System.out.println("Shipping cost: " + ShipCost);
			Assert.assertTrue(true);
		}
		catch(Exception e)
		{
			e.getMessage();
			System.out.println("Shipping cost is not generated");
			Assert.assertTrue(false);
		}

		boolean rdShippingCost = driver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']")).isSelected();
		if(!rdShippingCost)
		{
			driver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']")).click();
		}


		driver.findElement(By.xpath("//button[@type='submit' and @title='Update Total']")).click();

		double lblGrandTotal = Double.parseDouble(ShipCost) + Double.parseDouble(subTotal);
		String strGrandTotal = String.valueOf(lblGrandTotal);
		System.out.println("Total cost (Shipping + Product cost) :" + strGrandTotal);

		String actualGrandTotal = driver.findElement(By.xpath("//div[@class='cart-totals']//child::strong/span")).getText();
		actualGrandTotal = actualGrandTotal.replace("$", "");
		actualGrandTotal = actualGrandTotal.trim();
		
		if(strGrandTotal.equals(actualGrandTotal))
		{
			System.out.println("Shipping cost added to total product cost");
		}

		driver.findElement(By.xpath("//button[@title='Proceed to Checkout']")).click();
	}

	@Test(priority = 3)
	public void shippingInfo() throws InterruptedException
	{
		Thread.sleep(7000);
		closePlayer();
		//Ship to different address radio button
		driver.findElement(By.xpath("//input[@type=\"radio\" and @id=\"billing:use_for_shipping_no\"]")).click();
		
		//Continue button
		driver.findElement(By.xpath("//div[@id=\"billing-buttons-container\"]//child::button")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("billing:street1")).clear();
		driver.findElement(By.id("billing:street1")).sendKeys("ABC");

		driver.findElement(By.id("billing:city")).clear();
		driver.findElement(By.id("billing:city")).sendKeys("New York");

		Select state = new Select(driver.findElement(By.id("billing:region_id")));
		state.selectByVisibleText("New York");

		driver.findElement(By.id("billing:postcode")).clear();
		driver.findElement(By.id("billing:postcode")).sendKeys("542896");

		Select country = new Select(driver.findElement(By.id("billing:country_id")));
		country.selectByVisibleText("United States");

		driver.findElement(By.id("billing:telephone")).clear();
		driver.findElement(By.id("billing:telephone")).sendKeys("12345678");

		//Continue button
		driver.findElement(By.xpath("//div[@id=\"billing-buttons-container\"]//child::button")).click();
		Thread.sleep(2000);
		
		//Use Billing Address checkbox
		driver.findElement(By.id("shipping:same_as_billing")).click();

		//Continue Button
		driver.findElement(By.xpath("//div[@id='shipping-buttons-container']//button")).click();
		Thread.sleep(2000);

		//Continue Button
		driver.findElement(By.xpath("//div[@id='shipping-method-buttons-container']//button")).click();
		Thread.sleep(2000);
	}

	@Test(priority = 4)
	public void paymentInfo() throws InterruptedException
	{
		driver.findElement(By.xpath("//dt[@id='dt_method_checkmo']//input")).click();
		driver.findElement(By.xpath("//div[@id='payment-buttons-container']//button")).click();
		Thread.sleep(2000);
	}

	@Test(priority = 5)
	public void Order() throws InterruptedException
	{
		driver.findElement(By.xpath("//button[@class='button btn-checkout']")).click();
		Thread.sleep(2000);
		String ordermag = driver.findElement(By.xpath("//div[@class='col-main']//h1")).getText();
		if(ordermag.equals("YOUR ORDER HAS BEEN RECEIVED."))
		{
			System.out.println("Order placed successfully");
			String orderNo = driver.findElement(By.xpath("//div[@class='col-main']//p[1]/a")).getText();
			System.out.println("Order number is: "+ orderNo);
		}
		else
		{
			System.out.println("Order is not placed");
		}
	}

}
