package testCases;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import utility.ReadConfig;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();
	public String baseurl = readConfig.getApplicationURL();
	public String username = readConfig.getUserName();
	public String password = readConfig.getPassword();
	public String browser = readConfig.getBrowser();
	public String iepath = readConfig.getIEPath();
	public String chromepath = readConfig.getChromePath();
	public String firefoxpath = readConfig.getFirefoxPath();
	public static WebDriver driver;
	public static Logger logger;
	
	
	@BeforeClass
	public void setup()
	{
		logger = Logger.getLogger("BaseClass");
		
		if(browser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", chromepath);
			logger.info("Launching chrome browser");
			driver = new ChromeDriver();
		}else if(browser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", firefoxpath);
			logger.info("Launching firefox browser");
			driver = new FirefoxDriver();
		}else if(browser.equals("IE"))
		{
			System.setProperty("webdriver.ie.driver", iepath);
			logger.info("Launching IE browser");
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		logger.info("Navigating to url");
		driver.get(baseurl);
	}
	
	@AfterClass
	public void tearDown()
	{
		logger.info("closing the browser");
		driver.quit();
	}
	
	public static String captureScreenshot(WebDriver driver, String tname)
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir")+"/Screenshots/"+tname+".png";
		File target = new File(filePath);
		try {
		FileUtils.copyFile(source, target);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filePath;
	}
	
	public void closePlayer()
	{
		try {
			List<WebElement> frame = driver.findElements(By.tagName("iframe"));
			for (WebElement webElement : frame) {
				String frameName = webElement.getAttribute("id");
				//primis_playerSekindoSPlayer
				if (frameName.contains("flow_close_btn_iframe"))
				{
					driver.switchTo().frame(frameName);
					driver.findElement(By.id("closeBtn")).click();
					driver.switchTo().defaultContent();
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.getMessage();
		}
	}
}
