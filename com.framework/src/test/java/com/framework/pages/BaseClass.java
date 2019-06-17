package com.framework.pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.framework.utilities.*;

public class BaseClass {
	
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigfileLoader config;
	public ExtentReports report;
	public ExtentTest logger;
	
	@BeforeSuite
	public void setupsuite() throws Exception {
		
		excel = new ExcelDataProvider();
		config = new ConfigfileLoader();
		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File("./Reports/Facebook.html"));
		report = new ExtentReports();
		report.attachReporter(extent);
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
		if(result.getStatus() == ITestResult.FAILURE) {
			Helper.CaptureScreenshot(driver);
		}
		report.flush();
	}
}
