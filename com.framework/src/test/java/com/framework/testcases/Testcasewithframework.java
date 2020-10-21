package com.framework.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.framework.pages.BaseClass;
import com.framework.pages.HomePage;
import com.framework.pages.LoginPage;

public class Testcasewithframework extends BaseClass {
	
@Test

@Parameters ({"url"})
public void test(String url) throws Exception {
	
	logger = report.createTest("Login to Facebook");
	LoginPage login = PageFactory.initElements(driver, LoginPage.class);
	HomePage home = PageFactory.initElements(driver, HomePage.class);
	logger.info("Started the test");
	String pathOfScreenShot = System.getProperty("user.dir") + "\\Screenshot\\Screenshot.png";
	logger.addScreenCaptureFromPath(pathOfScreenShot, "Login to Facebook");
	login.login_page(excel.getStringdata("Login", 0, 0), excel.getStringdata("Login", 0, 1), url);
	//home.notify(driver, logger);
	logger.info("Operation performed successfully");
	home.tab_switching(driver, logger);
	logger.pass("Operation performed successfully");
	logger.pass("Done successfully");
}
}
