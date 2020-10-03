package testCases;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageObjects.ListPage;

public class test {
	public static WebDriver driver;

	public static void main(String[] args) {
		String[] myArray= {"sudha","sadhana","Aadhav","yog"};
		
		String beforeSort = Arrays.toString(myArray);
		System.out.println(beforeSort);
		
		Arrays.sort(myArray);
		String afterSort = Arrays.toString(myArray);
		System.out.println(afterSort);
		
		
	}

}
