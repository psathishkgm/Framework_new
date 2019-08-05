package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class HomePage {
	
@Test
public void notify(WebDriver driver, ExtentTest logger) throws Exception
{
	WebElement notify_scroll = driver.findElement(By.xpath("//*[@data-tooltip-content='Notifications']"));
	new Actions(driver).moveToElement(notify_scroll).click().build().perform();
	logger.pass("clicked on notifications");
}
@Test
public void see_all(WebDriver driver, ExtentTest logger) throws Exception {
	
	WebElement see_all = driver.findElement(By.xpath("(//a[@class='seeMore']//span[text()='See All'])[2]"));
	new Actions(driver).moveToElement(see_all).click().build().perform();
	logger.pass("Clicked on See all");
}
}
