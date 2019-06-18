package com.framework.pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.framework.utilities.*;

public class BaseClass {
	
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigfileLoader config;
	public ExtentReports report;
	public ExtentTest logger;
	
	@BeforeSuite // this will run at beginning of starting the test
	public void setupsuite() throws Exception {
		
		Reporter.log("Setup will do",true);
		excel = new ExcelDataProvider();
		config = new ConfigfileLoader();
		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File("./Reports/Facebook "+Helper.getCurrentDate()+".html"));
		report = new ExtentReports();
		report.attachReporter(extent);
		Reporter.log("Setup done and test cases are executing",true);
	}
	
	@BeforeClass // before starting test run once
	@Parameters ({"browser","url"})
	public void setup(String browser,String url) {
	
	Reporter.log("Browser launching",true);
	
	driver = BrowserFactory.startBrowser(driver,browser, url);
	
	Reporter.log("Browser lauched",true);
	
	}
	
	@AfterClass // after completion of entire test only run  once
	public void teardown() {
	
	   Reporter.log("Quiting",true);
		
	   BrowserFactory.quitbrowser(driver);
	   
	   Reporter.log("Quiting done",true);
	  
	}
	
	@AfterMethod // if 2 test means 2 times will run
	public void teardownMessage(ITestResult result) throws Exception {
		
		Reporter.log("Test in After method",true);
		
		if(result.getStatus() == ITestResult.FAILURE) {
			logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.CaptureScreenshot(driver)).build());
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
				logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.CaptureScreenshot(driver)).build());
		}
		report.flush();
		
		Reporter.log("Test completed and Reports generated",true);
	}
}
