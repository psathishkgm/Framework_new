package com.framework.utilities;

import java.io.File;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helper {
	
	private static String timestamp;
	private static String time;
	
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

			time = getCurrentDateTime();

			pathOfScreenShot = System.getProperty("user.dir") + "\\Screenshot\\Screenshot" + time + ".png";

			FileUtils.copyFile(scrFile, new File(pathOfScreenShot));

		} catch (Exception e) {

			System.out.println("Screenshot Failed "+e.getMessage());
		}

		return pathOfScreenShot;
	}


}
