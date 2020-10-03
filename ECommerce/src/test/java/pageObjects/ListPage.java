package pageObjects;

import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ListPage {
	public WebDriver driver;

	public ListPage(WebDriver r)
	{
		driver = r;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[text()='Mobile']")
	public WebElement lnkMobile;

	@FindBy(xpath = "//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div/select")
	public WebElement dpSortBy;

	@FindBy(className = "product-name")
	public List<WebElement> productNames;

	@FindBy(xpath ="//a[@class='product-image']")
	public List<WebElement> productLink;

	@FindBy(xpath="//span[@class='price']")
	public List<WebElement> productPrice;



	public void selectMobile()
	{
		Select select = new Select(dpSortBy);
		select.selectByVisibleText("Name");
	}

	public void clkMobile()
	{
		lnkMobile.click();
	}


	public int productCount(List<WebElement> ele)
	{
		int totalProducts = ele.size();
		return totalProducts;
	}

	public String[] getProductNames(int productCount)
	{
		String[] arrProducts = new String[productCount];
		int i = 0;
		for (WebElement webElement : productNames) {
			arrProducts[i] = webElement.getText();
			i++;
		}
		return arrProducts;
	}

	
	public void clkProduct(int productIndex)
	{
		productLink.size();
}
}
