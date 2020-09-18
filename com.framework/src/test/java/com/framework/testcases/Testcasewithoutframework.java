package com.framework.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Testcasewithoutframework {
	
@Test
public void test() throws Exception {
	
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\ksathish\\git\\Framework_new\\com.framework\\Drivers\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.get("https://github.com/");
	driver.findElement(By.xpath("//a[contains(@class,'HeaderMenu-link') and contains(text(),'Sign')][1]")).click();
	Thread.sleep(2000);
	driver.findElement(By.name("login")).sendKeys("psathishkgm@gmail.com");
	driver.findElement(By.name("password")).sendKeys("Sathish&1991");
	driver.findElement(By.name("commit")).submit();
	Thread.sleep(2000);
	driver.close();
	
}

}
