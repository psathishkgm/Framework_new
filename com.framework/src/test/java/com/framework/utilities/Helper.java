package com.framework.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Helper {
	
	private static String timestamp;
	//private static String time;
		
	public static String getCurrentDate()
	{
		DateFormat date = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		
		return date.format(currentDate);
	}
	
	public static String getCurrentDateTime() {

		Calendar cal = Calendar.getInstance();
		timestamp = Integer.toString(cal.get(Calendar.DATE)) + Integer.toString(cal.get(Calendar.MONTH) + 1)
				+ Integer.toString(cal.get(Calendar.YEAR)) + Integer.toString(cal.get(Calendar.HOUR))
				+ Integer.toString(cal.get(Calendar.MINUTE)) + Integer.toString(cal.get(Calendar.SECOND));

		return timestamp;
	}
	
	public static String CaptureScreenshot(WebDriver driver) {

		String pathOfScreenShot = null;
		try {
			
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			pathOfScreenShot = System.getProperty("user.dir") + "\\Screenshot\\Screenshot" + getCurrentDate() + ".png";

			FileUtils.copyFile(scrFile, new File(pathOfScreenShot));

		} catch (Exception e) {

			System.out.println("Screenshot Failed "+e.getMessage());
		}

		return pathOfScreenShot;
	}

	public static void clickElement(WebDriver driver, String value, By xpath) {
		WebElement ele = driver.findElement(xpath);
		highlightElement(driver, ele);
		ele.sendKeys(value);
	}
	
	public static void clickfunction(WebDriver driver, By xpath) {
		WebElement ele = driver.findElement(xpath);
		highlightElement(driver, ele);
		ele.click();
	}
	
	public static void highlightElement(WebDriver driver, WebElement element)
	{
	JavascriptExecutor js=(JavascriptExecutor)driver; 
	js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	try 
	{
	Thread.sleep(1000);
	} 
	catch (InterruptedException e) {
	 
	System.out.println(e.getMessage());
	} 	 
	}

}
