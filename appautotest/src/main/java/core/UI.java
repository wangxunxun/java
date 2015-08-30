package core;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.CommonTools;
import core.Initial;
public class UI extends Initial{
    
    public void clickElement(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
    	findElement(eledata, page, name).click();
    }
    
    public void sendKeys(Map<String, Map<String, Map<String, String>>> eledata,String page,String name,String value){
    	findElement(eledata, page, name).sendKeys(value);
    }
    
    public String getElementText(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
    	String value = findElement(eledata, page, name).getText();
    	if(value !=null){
    		return value;
    	}
    	else{
    		tool.log(name+":No text.");
    	}
    	return null;
    }
    
    protected void executeJavaScript(String js, WebElement element) {
        ((JavascriptExecutor) driver).executeScript(js, element);
    }
    
    public void assertEquals(String actual, String expected){
    	Assert.assertEquals(actual, expected);
    }
    

    
    public boolean isSlected(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
    	return findElement(eledata, page, name).isSelected();
    }
    
    public boolean isDisplayed(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
    	return findElement(eledata, page, name).isDisplayed();
    }
    
    public boolean isEnabled(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
    	return findElement(eledata, page, name).isEnabled();
    }
    


	public WebElement findElement(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
		String selecttype = eledata.get(page).get(name).get("SelectType");
		String location = eledata.get(page).get(name).get("Location");
		if (selecttype.equals("css")){
			return driver.findElement(By.cssSelector(location));
		}
		else if (selecttype.equals("id")){
			return driver.findElement(By.id(location));
		}
		else if (selecttype.equals("xpath")){
			return driver.findElement(By.xpath(location));
		}
		else if (selecttype.equals("name")){
			return driver.findElement(By.name(location));

		}
		else if (selecttype.equals("linktext")){
			return driver.findElement(By.linkText(location));

		}
		else{
			System.out.println("Can not find the element.");
		}
		return null;
	}
	
    public void waitDisplay(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
		String selecttype = eledata.get(page).get(name).get("SelectType");
		String location = eledata.get(page).get(name).get("Location");
		if (selecttype.equals("css")){
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(location)));

		}
		else if (selecttype.equals("id")){
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(location)));
		}
		else if (selecttype.equals("xpath")){
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(location)));
		}
		else {
			System.out.println("Can not find the element.");
		}
    }

}
