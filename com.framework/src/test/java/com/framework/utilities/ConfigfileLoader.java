package com.framework.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigfileLoader {
	
	Properties pro = new Properties();
	
	public ConfigfileLoader() {
		
		File src = new File("./Config/config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(src);
			pro.load(fis);
			
		} catch (Exception e) {
			
			System.out.println("Not loaded properties file >>>" + e.getMessage());
		}
	}
	
	public String textTosearch(String searchText) {
		
		return pro.getProperty(searchText);
	}

	public String getBrowser() {
		
		return pro.getProperty("browser");
	}
	
	public String getURL() {
		
		return pro.getProperty("url");
	}
}
