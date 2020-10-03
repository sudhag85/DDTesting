package utility;


import java.awt.Color;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext context){
		
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy.hh.mm.ss");
		String timeStamp = df.format(date);
		String report = "Report-"+timeStamp+".html";
						
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/"+report);
		htmlReporter.loadConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		htmlReporter.config().setReportName("Automation Report");
		htmlReporter.config().setDocumentTitle("ECommerce Test Results");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Host", "LocalHost");
		extent.setSystemInfo("User", "Sudha");
		
	}

	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest(result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" executed successfully", ExtentColor.GREEN) );
	}

	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+ " didnt execute completely due to failure" , ExtentColor.RED) );
		
		String filePath = System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+".png";
		File f = new File(filePath);
		System.out.println("File path is: "+f);
		
		if(f.exists())
		{
			try
			{
				test.fail("Screenshot is below: "+ test.addScreenCaptureFromPath(filePath));				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public void onTestSkipped(ITestResult result)
	{
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" didn't execute", ExtentColor.ORANGE) );
	}

	public void onFinish(ITestContext context)
	{
		extent.flush();
	}

}
