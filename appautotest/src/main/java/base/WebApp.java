package base;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.UI;

public class WebApp extends UI{
	protected String main_window;
	
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

    public String getElementValue(String page,String name){
    	String value = findElement(page, name).getAttribute("value");
    	if(value !=null){
    		return value;
    	}
    	else{
    		tool.log(name+":No text.");
    	}
    	return null;
    }
    		    
    public void getScreen(String filename)
    {


		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	  	if (!(new File(dirName).isDirectory())) {  // 判断是否存在该目录
	  		new File(dirName).mkdir();  // 如果不存在则新建一个目录
	  	}
		String filepath = dirName+tool.getCurrentTime()+ "_"+ filename+  ".jpg";
		try {
			System.out.println("save snapshot path is:"+dirName+filename);
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
    
    public void getScreen()
    {
    	getScreen("");
	}

    
    public boolean isAlertPresent(){
        tool.sleep(500);
        try {
            driver.switchTo().alert().accept();
            return true;
        } catch (Exception e) {
            return false;
        }
    } 
    
    public static void scrollTo(int height){  
        try {  
            String setscroll = "document.documentElement.scrollTop=" + height;               
            JavascriptExecutor jse=(JavascriptExecutor) driver;  
            jse.executeScript(setscroll);  
        } catch (Exception e) {  
            System.out.println("Fail to set the scroll.");  
        }             
    }   

    
    public void srollToBottom(){
    	scrollTo(10000);
    }
    

    
    public void switchToNewWindow() {
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(driver.getWindowHandle())) {
            	driver.switchTo().window(window);
            }
        }
    }
    
    public void srollToElement(String page,String name){
    	executeJavaScript("arguments[0].scrollIntoView()", findElement(page, name));
    }
    
    public void clickElementByJS(String page,String name) {

        // String locator = uiMapElementLocator(elementName);
        WebElement element = findElement( page, name);
        executeJavaScript("arguments[0].click();", element);
    }
    


    
    public void moveMouseOn(String page,String name) {

        WebElement element = findElement(page, name);
        tool.log("Moving mouse to element: " + name + ".");
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }
    
    public void switchToMainWindow() {
        Set<String> getWindows = driver.getWindowHandles();
		for (String win : getWindows) {
			main_window = win;
			break;
		}

        for (String win : getWindows) {
            if (!win.equals(main_window)) {
            	driver.switchTo().window(win);
                driver.close();
            }
        }
        driver.switchTo().window(main_window);

    }
  
    public void KeyPress(int key) {
        Robot robot;
        try {
            robot = new Robot();
            robot.keyPress(key);
            robot.keyRelease(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    

    public void runChormeApp(){
		String dirs=tool.setPath("/testresource/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", dirs);
    	driver=new ChromeDriver();
    	wait = new WebDriverWait(driver,waitTime);
    }
     
    public void switchToFrame(String nameOrId){
    	driver.switchTo().frame(nameOrId);
    }
    
    public void switchToFrame(int index){
    	driver.switchTo().frame(index);
    }
    
    public void switchToFrame(WebElement frameElement){
    	driver.switchTo().frame(frameElement);
    }

    public void switchToDefaultContent(){
    	driver.switchTo().defaultContent();
    }
    

    
    

}
