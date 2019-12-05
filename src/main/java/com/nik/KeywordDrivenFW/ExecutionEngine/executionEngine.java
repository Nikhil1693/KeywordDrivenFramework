package com.nik.KeywordDrivenFW.ExecutionEngine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.nik.KeywordDrivenFW.base.TestBase;

public class executionEngine {

	public WebDriver driver;
	public Properties config;
	public static Workbook book;
	public static Sheet sheet;
	public TestBase tb;
    
	
	public final String SCENARIO_PATH="D:\\Nikhil Docs\\eclipse workspace\\KeywordDrivenFW\\src\\main\\java\\com\\nik\\KeywordDrivenFW\\scenarios\\scenarios.xlsx";
	
	public void startExecution(String sheetName) {
	
		FileInputStream file=null;
	try {
		file=new FileInputStream(SCENARIO_PATH);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	
		try {
			book=WorkbookFactory.create(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	sheet=book.getSheet(sheetName);
	int k=0;
	
	for(int i=0;i<sheet.getLastRowNum();i++) {
		try {
			String locatorType=sheet.getRow(i+1).getCell(k+1).toString().trim();
			String locatorValue=sheet.getRow(i+1).getCell(k+2).toString().trim();	
			String action=sheet.getRow(i+1).getCell(k+3).toString().trim();
			String value=sheet.getRow(i+1).getCell(k+4).toString().trim();
			
			switch (action) {
			case "openbrowser":
				 tb=new TestBase();
				 config=tb.init_properties();
				 if(value.isEmpty()||value.equalsIgnoreCase("NA")) {
					 tb.init_driver(config.getProperty("browser"));
				 }
				 else {
					 driver=tb.init_driver(value);
				 }
				 break;
				 
			case "enter url":
				if(value.isEmpty()||value.equalsIgnoreCase("NA")) {
					driver.get(config.getProperty("url"));
				}
				else {
					driver.get(value);
				}
				break; 
				
			case "quit":
				driver.quit();
				break;
				
			default:
				break;
			}
			
			switch (locatorType) {
			case "id":
				WebElement element=driver.findElement(By.id(locatorValue));
				if(action.equalsIgnoreCase("sendkeys")) {
					Thread.sleep(3000);
					element.clear();
					element.sendKeys(value);
					}
				else if(action.equalsIgnoreCase("click")) {
					element.click();
				}else if(action.equalsIgnoreCase("isDisplayed")) {
					element.isDisplayed();
				}else if(action.equalsIgnoreCase("gettext")) {
					String elementText=element.getText();
					System.out.println("element from gettext" +elementText);
				}
				locatorType=null;
				break;
				
			case "xpath":
				element=driver.findElement(By.xpath(locatorValue));
				if(action.equalsIgnoreCase("sendkeys")) {
					Thread.sleep(3000);
					element.clear();
					element.sendKeys(value);
					}
				else if(action.equalsIgnoreCase("click")) {
					element.click();
				}else if(action.equalsIgnoreCase("isDisplayed")) {
					element.isDisplayed();
				}else if(action.equalsIgnoreCase("gettext")) {
					String elementText=element.getText();
					System.out.println("element from gettext" +elementText);
				}
				locatorType=null;
				break;
				
			case "name":
				 element=driver.findElement(By.className(locatorValue));
				if(action.equalsIgnoreCase("sendkeys")) {
					Thread.sleep(3000);
					element.clear();
					element.sendKeys(value);
					}
				else if(action.equalsIgnoreCase("click")) {
					element.click();
				}else if(action.equalsIgnoreCase("isDisplayed")) {
					element.isDisplayed();
				}else if(action.equalsIgnoreCase("gettext")) {
					String elementText=element.getText();
					System.out.println("element from gettext" +elementText);
				}
				locatorType=null;
				break;
				
			case "cssSelector":
				 element=driver.findElement(By.cssSelector(locatorValue));
					if(action.equalsIgnoreCase("sendkeys")) {
						Thread.sleep(3000);
						element.clear();
						element.sendKeys(value);
						}
					else if(action.equalsIgnoreCase("click")) {
						element.click();
					}else if(action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					}else if(action.equalsIgnoreCase("gettext")) {
						String elementText=element.getText();
						System.out.println("element from gettext" +elementText);
					}
					locatorType=null;
					break;
			

			default:
				break;
			}
		}
		catch(Exception e) {
	}
	

}
}
}