package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
	  driver.findElement(mail).sendKeys(uname);
	  driver.findElement(password).sendKeys(passw);
	  driver.findElement(login).click();
	  Thread.sleep(2000);
	  
  }
}
