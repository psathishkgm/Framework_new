package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.framework.utilities.*;

public class BaseClass {
	
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigfileLoader config;
	
	@BeforeSuite
	public void setupsuite() throws Exception {
		
		excel = new ExcelDataProvider();
		config = new ConfigfileLoader();
	}
	
	@BeforeClass
	public void setup() {
	
	driver = BrowserFactory.startBrowser(driver,config.getBrowser(), config.getURL());
	
	}
	
	@AfterClass
	public void teardown() {
	
	  BrowserFactory.quitbrowser(driver);
	  
	}
	
	@AfterMethod
	public void teardownMessage(ITestResult result) {
		if(result.getStartMillis() == ITestResult.FAILURE) {
			Helper.CaptureScreenshot(driver);
		} else if (result.getStartMillis() == ITestResult.SUCCESS) {
           System.out.println("Test Completed Successfully");
		}
	}
}
