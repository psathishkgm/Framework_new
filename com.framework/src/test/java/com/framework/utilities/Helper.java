package com.framework.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

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

	public static WebElement getElementByProperty(WebDriver driver, By locator, int time) {
		@SuppressWarnings("deprecation")
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(time, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(Throwable.class);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		WebElement element = driver.findElement(locator);
		elementHighlight(driver, element, 8);
		return element;
	}
		
	public static void elementHighlight(WebDriver driver, WebElement Webelement,int wait_time) {
		String originalStyle = Webelement.getAttribute("style");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid yellow;');",
				Webelement);
		jse.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", Webelement);
	}
}
