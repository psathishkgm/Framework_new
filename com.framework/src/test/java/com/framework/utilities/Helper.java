package com.framework.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Helper {
	
	private static String timestamp;
	
	public static String getCurrentDate()
	{
		DateFormat date = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		
		return date.format(currentDate);
	}
	
	public static String getCurrentDateTime() {
                // Getting the current date and time
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
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(time))
				.pollingEvery(Duration.ofSeconds(time)).ignoring(Throwable.class);
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
	
	public static WebElement getElementByProperty(WebDriver driver, String property_value, int time) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(time))
				.pollingEvery(Duration.ofSeconds(time)).ignoring(Throwable.class);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath((property_value))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath((property_value))));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath((property_value))));
		WebElement element = driver.findElement(By.xpath((property_value)));
		elementHighlight(driver, element, 8);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return element;
	}
	
	@SuppressWarnings("deprecation")
	public static void enterTextUsingJavaScriptExecutor(WebDriver driver, By locator, String text, String locatorType) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		if (locatorType.equals("xpath")) {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			WebElement ele = driver.findElement(locator);
			js.executeScript("arguments[0].value='" + text + "'", ele);
		}   
	}
	
	public static WebElement getElementByXpath(WebDriver driver, String locator, int time) throws InterruptedException {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(time))
				.pollingEvery(Duration.ofSeconds(time)).ignoring(Throwable.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
		WebElement element = driver.findElement(By.xpath(locator));
		elementHighlight(driver, element,8);
		Thread.sleep(500);
		return element;
	}
	
	public static WebElement getElementByClassName(WebDriver driver, String locator, int time) throws InterruptedException {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(time))
				.pollingEvery(Duration.ofSeconds(time)).ignoring(Throwable.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));
		wait.until(ExpectedConditions.elementToBeClickable(By.className(locator)));
		WebElement element = driver.findElement(By.className(locator));
		elementHighlight(driver, element,8);
		Thread.sleep(500);
		return element;
	}
	
	@SuppressWarnings("deprecation")
	public static void webDriverwait_keyvalue(WebDriver driver,String text) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		// explicit wait for property values
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath((text))));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath((text))));
	}

	@SuppressWarnings("deprecation")
	public static void webDriverwait_locator(WebDriver driver,String locator, String locatorType) throws Exception {
				
		WebDriverWait wait = new WebDriverWait(driver, 100);
		// explicit wait for locator values
		if (locatorType.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
		} else if (locatorType.equalsIgnoreCase("classname")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));
			wait.until(ExpectedConditions.elementToBeClickable(By.className(locator)));
		}
	}
	
	public static boolean DownloadFileVerify(WebDriver driver,String property) throws Exception {
		Thread.sleep(5000);
		String series_title = driver.findElement(By.xpath((property))).getText();
		String downloadPath = System.getProperty("user.dir");
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();
		for (int i = 0; i < dirContents.length; i++) {
			if (dirContents[i].getName().contains(series_title)) {
				// File has been found, it can now be deleted:
				dirContents[i].delete();
				System.out.println("File has been download to Excel and its verified");
				return true;
			}
		}
		Assert.fail("Download to Excel verification failed");
		return false;
	}
	
	public static void SwitchtonewWindow(WebDriver driver,String childURL,String parentURL) throws Exception {
		ArrayList<String> child = new ArrayList<String>(driver.getWindowHandles());
		String parent = driver.getWindowHandle();
		driver.get(parentURL);
		//driver.switchTo().window(child.get(1));
		WebDriver newWindow = driver.switchTo().newWindow(WindowType.TAB);
		newWindow.get(childURL);
		driver.switchTo().window(child.get(1)).close();
		driver.switchTo().window(parent);
		
	}
}
