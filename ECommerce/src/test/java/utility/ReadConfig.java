package utility;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	public Properties properties;

	public ReadConfig() {
		try 
		{
			String filePath = System.getProperty("user.dir")+"/Configuration/config.properties";
			FileInputStream fis = new FileInputStream(filePath);
			properties = new Properties();
			properties.load(fis);	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public String getApplicationURL()
	{
		String baseurl = properties.getProperty("baseurl");
		return baseurl;
	}
	
	public String getUserName()
	{
		String username = properties.getProperty("username");
		return username;
	}
	
	public String getPassword()
	{
		String password = properties.getProperty("password");
		return password;
		
	}
	
	public String getBrowser()
	{
		String browser = properties.getProperty("browser");
		return browser;
	}
	
	public String getIEPath()
	{
		String iePath = properties.getProperty("iepath");
		return iePath;
	}
	
	public String getChromePath()
	{
		String chromePath = properties.getProperty("chromepath");
		return chromePath;
	}
	
	public String getFirefoxPath()
	{
		String firefoxPath = properties.getProperty("firefoxpath");
		return firefoxPath;
	}
	

}
