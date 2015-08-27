package base;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import core.Initial;
public class WebApp extends Initial{
	public static ChromeDriver driver;	
	public WebDriverWait wait; 
	
	
	public WebElement findElement(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
		String selecttype = eledata.get(page).get(name).get("SelectType");
		String location = eledata.get(page).get(name).get("Location");
		if (selecttype.equals("css")){
			return driver.findElementByCssSelector(location);
		}
		else if (selecttype.equals("id")){
			return driver.findElementById(location);
		}
		else if (selecttype.equals("xpath")){
			return driver.findElementByXPath(location);
		}
		else{
			System.out.println("Can not find the element.");
		}
		return null;
	}
	

	
	
	
	public void switchToNewPage(){
    	for(String winHandle : driver.getWindowHandles()){  
            driver.switchTo().window(winHandle);  
        }
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
    
    public void checkString(String actual, String expected){
    	Assert.assertEquals(actual, expected);
    }
    
    public static void  maxBrowser(ChromeDriver driver){  
        try {  
            String maxBroswer = "if (window.screen) {window.moveTo(0, 0);" +  
                    "window.resizeTo(window.screen.availWidth,window.screen.availHeight);}";  
              
            JavascriptExecutor jse=(JavascriptExecutor) driver;  
            jse.executeScript(maxBroswer);  
        } catch (Exception e) {  
            System.out.println("Fail to  Maximization browser");  
        }  
    } 
    
    public static void setScroll(ChromeDriver driver,int height){  
        try {  
            String setscroll = "document.documentElement.scrollTop=" + height;  
              
            JavascriptExecutor jse=(JavascriptExecutor) driver;  
            jse.executeScript(setscroll);  
        } catch (Exception e) {  
            System.out.println("Fail to set the scroll.");  
        }             
    }   
    
    public void rollToBottom(){
        String  js="var q=document.documentElement.scrollTop=10000";
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        jsExecutor.executeScript(js);
    }
}
