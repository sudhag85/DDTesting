package testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ListPage;

public class TC001_Verify_MobileSorting extends BaseClass {

	@Test
	public void sortMobileByName() throws InterruptedException
	{
		logger.info("Verifying home page title");
		Assert.assertEquals(driver.getTitle(), "Home page");
		ListPage lp = new ListPage(driver);
		logger.info("Clicking Mobile link");
		lp.clkMobile();
		Thread.sleep(2000);
		
		logger.info("Verifying mobile page title");
		Assert.assertEquals(driver.getTitle(), "Mobile");
		
		
		logger.info("Selecting name from sort by dropdown");
		lp.selectMobile();
		int totalProducts = lp.productCount(lp.productNames);		//Total products listed in page
		System.out.println("Total no. of mobiles displayed is: " + totalProducts);
			
		String[] Products = lp.getProductNames(totalProducts);// Product Names in String Array
		
		String productsOrderOnLoad = Arrays.toString(Products);
		System.out.println("Mobiles order before sorting by name : "+productsOrderOnLoad);
		
		Arrays.sort(Products);
		String tempArray = Arrays.toString(Products);
		System.out.println("Mobiles order after sorting by name : " + tempArray);
		
		if (productsOrderOnLoad.equals(tempArray))
		{
			System.out.println("Mobiles are sorted by name");
			Assert.assertTrue(true);
		}else {
			System.out.println("Mobiles are sorted by name");
			captureScreenshot(driver,"Mobile not sorted by name");
			Assert.assertTrue(false);
		}
		
	}

}
