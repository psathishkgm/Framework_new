package com.framework.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.framework.pages.BaseClass;
import com.framework.pages.LoginPage;

public class Testcasewithframework extends BaseClass {
	
@Test

public void test() throws Exception {
	
	LoginPage login = PageFactory.initElements(driver, LoginPage.class);
	login.login_page(excel.getStringdata("Login", 0, 0), excel.getStringdata("Login", 0, 1));
}
}
