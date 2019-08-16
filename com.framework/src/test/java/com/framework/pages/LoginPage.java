package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.framework.utilities.Helper;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver ldriver) { // ldriver is the reference webDriver of main test case and store into local variable driver.
		this.driver = ldriver;
	}
	
 By mail = By.name("email");
 By password = By.name("pass");
 By login = By.id("loginbutton");

  
  public void login_page(String uname, String passw) throws Exception {
	  
	  Thread.sleep(2000);
	  Helper.clickElement(driver, uname, mail);
	  Helper.clickElement(driver, passw, password);
	  Helper.clickfunction(driver, login);
	  Thread.sleep(2000);
	  
  }
}
