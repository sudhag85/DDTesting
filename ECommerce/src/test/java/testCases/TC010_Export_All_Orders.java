package testCases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC010_Export_All_Orders {
	
	String path="C:\\Users\\65838\\Downloads";
	boolean flag;
	
	@Test(priority=1)
	public void exportOrders()
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.php/backendlogin");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("user01");
		driver.findElement(By.id("login")).clear();
		driver.findElement(By.id("login")).sendKeys("guru99com");
		driver.findElement(By.xpath("//div[@class='form-buttons']//input")).click();
		driver.findElement(By.xpath("//div[@class='message-popup-head']/a")).click();
				
		driver.findElement(By.xpath("//ul[@id='nav']//a[1]")).click();
		Actions action = new Actions(driver);
		WebElement order = driver.findElement(By.xpath("//li[@class='  level1']/a"));
		action.clickAndHold(order).release(order).build().perform();
		
		Select select = new Select(driver.findElement(By.id("sales_order_grid_export")));
		select.selectByVisibleText("CSV");
	
		driver.findElement(By.xpath("//button[@title='Export']")).click();
		
	}

	
	public boolean isDownloadedFileAvailable()
	{
		flag = false;
		File f =  new File(path);
		File[] files = f.listFiles();
		if((files.length==0)||(files==null))
		{
			System.out.println("The directory is empty");
			Assert.assertTrue(false);
			flag=false;
		}else{
			for (File file : files) {
				if(file.getName().equals("orders.csv"))
				{
					Assert.assertTrue(true);
				System.out.println("Expected file is present in directory");
				flag=true;
				break;
				}
			}
		}
		return flag;
	}
	
	@Test(priority=2)
	public void readFile() throws IOException
	{
		if(isDownloadedFileAvailable()) {
		File f = new File("C:\\Users\\65838\\Downloads\\orders.csv");
		FileReader fr =  new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String lines = br.readLine();
		while(lines!=null)
		{
			System.out.println(lines);
			lines=br.readLine();
		}
		}
		else
		{
			System.out.println("File not downloaded");
		}
	}
	
	
}
