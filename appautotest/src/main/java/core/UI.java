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
	public static WebDriver driver;	
//	public static ChromeDriver driver;
	public WebDriverWait wait; 
	public static CommonTools tool = new CommonTools();

	
    
    public void get(String url){
    	driver.get(url);
    }
    
    public void close(){
    	driver.close();
    }
    
    public void getTitle(){
    	driver.getTitle();
    }
    
    public void quit(){
    	driver.quit();
    }
    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }


    public String getCurrentWindow() {
        return driver.getWindowHandle();
    }
    
    public void clickElement(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
    	findElement(eledata, page, name).click();
    }
    
    public void sendKeys(Map<String, Map<String, Map<String, String>>> eledata,String page,String name,String value){
    	findElement(eledata, page, name).sendKeys(value);
    }
    
    public String getElementValue(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
    	String value = findElement(eledata, page, name).getAttribute("value");
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
//			return driver.findElementByCssSelector(location);
		}
		else if (selecttype.equals("id")){
			return driver.findElement(By.id(location));
//			return driver.findElementById(location);
		}
		else if (selecttype.equals("xpath")){
			return driver.findElement(By.xpath(location));
//			return driver.findElementByXPath(location);
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
    
    public static void getScreen(String filename)
    {


		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dir_name = tool.setCurrentPath("\\screenshot\\");
	  	if (!(new File(dir_name).isDirectory())) {  // 判断是否存在该目录
	  		new File(dir_name).mkdir();  // 如果不存在则新建一个目录
	  	}
		String filepath = dir_name+tool.getCurrentTime()+ "_"+ filename+  ".jpg";
		try {
			System.out.println("save snapshot path is:"+dir_name+filename);
			FileUtils.copyFile(scrFile, new File(filepath));
		   	} 
		catch (IOException e) {
			System.out.println("Can't save screenshot");
			e.printStackTrace();
		} 
		finally{
		     System.out.println("screen shot finished");
		}
	}
    
    public static void getScreen()
    {
    	getScreen("");
	}

    

}
