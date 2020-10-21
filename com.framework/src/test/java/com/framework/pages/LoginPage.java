package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

import com.framework.utilities.HTTPRequest;
import com.framework.utilities.Helper;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver ldriver) { // ldriver is the reference webDriver of main test case and store into local variable driver.
		this.driver = ldriver;
	}
	
 By mail = By.name("email");
 By password = By.name("pass");
 By login = By.id("loginbutton");

  @Parameters ({"url"})  
  public void login_page(String uname, String passw,String url) throws Exception {
	  
	  Helper.getElementByProperty(driver, mail, 2).sendKeys(uname);
	  Helper.getElementByProperty(driver, password, 2).sendKeys(passw);
	  Helper.getElementByProperty(driver, login, 2).click();
	  HTTPRequest.testStatusCode(url);
	  
  }
}
