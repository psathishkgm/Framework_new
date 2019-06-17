package com.framework.utilities;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class BrowserFactory {
	 		
	public static WebDriver startBrowser(WebDriver driver,String browser,String url) {

		if (browser.equalsIgnoreCase("FireFox")) {

			String DRIVER_PATH = "./Drivers/geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", DRIVER_PATH);
			FirefoxProfile p = new FirefoxProfile();
			p.setAcceptUntrustedCertificates(true);
			p.setAssumeUntrustedCertificateIssuer(false);
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("Chrome")) {

			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", "C:\\SeleniumDownloadFiles");
			String DRIVER_PATH ="./Drivers/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
            ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-extensions");
			options.setExperimentalOption("prefs", chromePrefs);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));	
			capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);	 
			capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);	
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		    ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File(DRIVER_PATH)).usingAnyFreePort().build();
		    options.merge(capabilities);
		    driver = new ChromeDriver(service,options);
					
		} else if (browser.equalsIgnoreCase("IE")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
			capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			String DRIVER_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", DRIVER_PATH);
			driver = new InternetExplorerDriver(capabilities);
		
		} else if (browser.equalsIgnoreCase("Safari")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
			capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			driver = new SafariDriver(capabilities);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					"C:\\Program Files (x86)\\Microsoft Web Driver\\MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		}

		else {

			Reporter.log("Incorrect 'Web Browser' name provided");

		}
		driver.manage().window().maximize();
	   	driver.get(url);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		return driver;

	}
	
	public static void quitbrowser(WebDriver driver) {
		
		driver.quit();
	}

}
