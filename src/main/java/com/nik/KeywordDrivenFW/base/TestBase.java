package com.nik.KeywordDrivenFW.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TestBase {

	public WebDriver driver;
	public Properties config;
	public FileInputStream file;
	
	
	public WebDriver init_driver(String browserName) {
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\Nikhil Docs\\eclipse workspace\\KeywordDrivenFW\\src\\main\\java\\executable\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		}else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\Nikhil Docs\\eclipse workspace\\KeywordDrivenFW\\src\\main\\java\\executable\\geckodriver.exe");
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		}
		return driver;
	}
	
	public Properties init_properties() {
		config=new Properties();
		try {
			file=new FileInputStream("D:\\Nikhil Docs\\eclipse workspace\\KeywordDrivenFW\\src\\main\\java\\properties\\config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			config.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return config;
	}
}
