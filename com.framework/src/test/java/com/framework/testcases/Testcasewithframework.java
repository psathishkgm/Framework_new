package com.framework.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.framework.pages.BaseClass;
import com.framework.pages.LoginPage;

public class Testcasewithframework extends BaseClass {
	
@Test

public void test() throws Exception {
	
	logger = report.createTest("Login to Facebook");
	LoginPage login = PageFactory.initElements(driver, LoginPage.class);
	logger.info("Started the test");
	login.login_page(excel.getStringdata("Login", 0, 0), excel.getStringdata("Login", 0, 1));
	logger.pass("done successfully");
}
}
