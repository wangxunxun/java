package base;
import java.awt.Robot;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.UI;
import utils.CommonTools;
import utils.OperateExcel;
import utils.TestngListenerWeb;


public class WebApp extends UI{
	protected String main_window;
	
	
    public void runChormeApp(){
    	
    	try {
			initialData();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	String dirs =null;
    	if(CommonTools.getOSName().matches("Mac OS X")){
    		dirs=CommonTools.setPath("/testresource/chromedriver");
    	}
    	else{
    		dirs=CommonTools.setPath("/testresource/chromedriver.exe");
    	}
	
		
		System.setProperty("webdriver.chrome.driver", dirs);
    	driver=new ChromeDriver();
    	wait = new WebDriverWait(driver,waitTime);


    }
     
    public void get(String url){
    	log("Get "+url+".");
    	driver.get(url);
    }
    

    
    public void getTitle(){
    	log("Get title.");
    	driver.getTitle();
    }
    
    public void quit(){

    	log("End the "+getClassName() +".");
		try {
			testCaseExcel.close();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			testReportExcel.close();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    	driver.quit();

/*    	List<Map<String, String>> classData = TestngListenerWeb.classData;
    	for(int i =0;i<classData.size();i++){
			String className=classData.get(i).get("className");
			String method=classData.get(i).get("method");
			String time=classData.get(i).get("time");
			String status=classData.get(i).get("status");
			String comment=classData.get(i).get("comment");
			System.out.println("className: "+className);
			System.out.println("method: "+method);
			System.out.println("time: "+time);
			System.out.println("status: "+status);
			System.out.println("comment: "+comment);
    	}*/
    	List<Map<String, String>> classData = new ArrayList<Map<String,String>>();
    	classData = TestngListenerWeb.classData;
    	TestngListenerWeb.classData.removeAll(TestngListenerWeb.classData);
    	OperateExcel testSummay;
		try {
			testSummay = new OperateExcel(testReportPath+testReportName+".xls", "TestSummary");
			try {
				testSummay.setFormat(10, true);
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	try {
				testSummay.writeTestToExcel(classData);
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				testSummay.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    	
/*        OperateExcel testSummary;
        try {
            testSummary = new OperateExcel(testReportPath+testReportName+".xls", "TestSummary");
            testSummary.readXML("/Users/wangxun/Documents/workspace/java/appautotest/testresource/Test_FCVerifySettings.xml");

            try {
                testSummary.close();
            } catch (WriteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (BiffException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/


    }
    
    public String getCurrentUrl() {
    	log("Get current url.");
        return driver.getCurrentUrl();
    }
    
    public Set<String> getWindowHandles() {
    	log("Get window handles.");
        return driver.getWindowHandles();
    }


    public String getCurrentWindow() {
    	log("Get current window handle.");
        return driver.getWindowHandle();
    }

    public String getElementValue(String page,String name){
    	log("Get the value of "+name+" element on the "+page+"page.");
    	String value = findElement(page, name).getAttribute("value");
    	if(value !=null){
    		return value;
    	}
    	else{
    		log(name+":No text.");
    	}
    	return null;
    }
    		    


    
    public boolean isAlertPresent(){
        sleep(500);
        try {
            driver.switchTo().alert().accept();
            return true;
        } catch (Exception e) {
            return false;
        }
    } 
    
    public void scrollTo(int height){  
        try {  
        	log("Scroll to "+height);
            String setscroll = "document.documentElement.scrollTop=" + height;               
            JavascriptExecutor jse=(JavascriptExecutor) driver;  
            jse.executeScript(setscroll);  
        } catch (Exception e) {  
            log("Fail to scroll.");  
        }             
    }   

    
    public void srollToBottom(){
    	scrollTo(10000);
    }
    

    
    public void switchToNewWindow() {
    	log("Switch to the new window.");
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(driver.getWindowHandle())) {
            	driver.switchTo().window(window);
            }
        }
    }
    
    public void srollToElement(String page,String name){
    	log("Scroll to the "+name+" element on the "+page+"page.");
    	executeJavaScript("arguments[0].scrollIntoView()", findElement(page, name));
    }
    
    public void clickElementByJS(String page,String name) {
    	log("Click the "+name+" element on the "+page+"page by JS.");

        // String locator = uiMapElementLocator(elementName);
        WebElement element = findElement( page, name);
        executeJavaScript("arguments[0].click();", element);
    }
    


    
    public void moveMouseOn(String page,String name) {

        WebElement element = findElement(page, name);
        log("Moving mouse to element: " + name + ".");
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }
    
    public void switchToMainWindow() {
    	log("Switch to the main window.");
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


    public void switchToFrame(String nameOrId){
    	log("Switch to the \""+nameOrId+"\" frame.");
    	driver.switchTo().frame(nameOrId);
    }
    
    public void switchToFrame(int index){
    	driver.switchTo().frame(index);
    }
    
    public void switchToFrame(WebElement frameElement){
    	driver.switchTo().frame(frameElement);
    }

    public void switchToDefaultContent(){
    	log("Switch to the default content.");
    	driver.switchTo().defaultContent();
    }
    
    public String getTableRowLocationByCss(String location,int index){
		
		StringBuffer sb = new StringBuffer(location);		
		int i = location.indexOf("tr:nth-child");
		sb.deleteCharAt(i+13);
		sb.insert(i+13,index);
		return sb.toString();
    }
    
    public void clickByCss(String selector){
    	driver.findElement(By.cssSelector(selector)).click();
    }
    
    public void clickByXpath(String xpathExpression){
    	driver.findElement(By.xpath(xpathExpression)).click();
    }
    
    public void sendKeysByCss(String selector,String keysToSend){
    	driver.findElement(By.cssSelector(selector)).sendKeys(keysToSend);
    }
    
    public void sendKeysByXpath(String xpathExpression,String keysToSend){
    	driver.findElement(By.xpath(xpathExpression)).sendKeys(keysToSend);
    }
    
    public void waitDisplayByCss(String selector){
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selector)));
    }

    public void waitDisplayByXpath(String xpathExpression){
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));
    }
    
    public void clearByCss(String selector){
    	driver.findElement(By.cssSelector(selector)).clear();
    }
    
    public void clearByXpath(String xpathExpression){
    	driver.findElement(By.xpath(xpathExpression)).clear();
    }
    
    @SuppressWarnings({ "unchecked" })
    public void runTestCase(String testCase){
	
    	List<Map<String,String>> cases = (List<Map<String, String>>) testCaseData.get(testCase);
		
		for (int i = 0;i <cases.size();i++){

			String action = cases.get(i).get("Action");
			String page = cases.get(i).get("Page");
			String name = cases.get(i).get("Element");
			String value = cases.get(i).get("Value");
			String actual = cases.get(i).get("Actual");
			String expected = cases.get(i).get("Expected");
			String row = cases.get(i).get("row");
			int rowin=Integer.parseInt(row);


			if (action.equals("click")){
				clickElement(page, name);
				logResult(rowin);
				writeResult(8, rowin, "P");
				String script = appClass+"."+"clickElement(\""+page+"\",\""+name+"\");";
				writeScript(9, rowin, script);


			}
			else if (action.equals("sleep")){
				int v=Integer.parseInt(value);
				sleep(v);
				logResult(rowin);
				writeResult(8, rowin, "P");
				String script = appClass+".sleep("+v+");";
				writeScript(9, rowin, script);

			}
			else if (action.equals("waitDisplay")){
				waitDisplay(page, name);
				logResult(rowin);
				writeResult(8, rowin, "P");
				String script = appClass+"."+"waitDisplay(\""+page+"\",\""+name+"\");";
				writeScript(9, rowin, script);
			}
			else if (action.equals("clear")){
				clear(page, name);
				logResult(rowin);
				writeResult(8, rowin, "P");
				String script = appClass+"."+"clear(\""+page+"\",\""+name+"\");";
				writeScript(9, rowin, script);
			}


			else if (action.equals("sendKey")){
				sendKeys(page, name, value);
				logResult(rowin);
				writeResult(8, rowin, "P");
				String script = appClass+"."+"sendKeys(\""+page+"\",\""+name+"\",\""+value+"\");";
				writeScript(9, rowin, script);
				

			}
			else if (action.equals("assert")){
				actual = getElementText(page, name);
				assertEquals(actual, expected);
				logResult(rowin);
				writeResult(8, rowin, "P");
				String script = appClass+"."+"assertEquals("+appClass+"."+"getElementText(\""+page+"\",\""+ name+"\")"+","+"\""+expected+"\");";
				writeScript(9, rowin, script);
				
			}
			else if (action.equals("get")){
				get(value);
				logResult(rowin);
				writeResult(8, rowin, "P");
				String script = appClass+"."+"get(\""+value+"\");";
				writeScript(9, rowin, script);
				
			}
			else if (action.equals("switchToFrame")){
				switchToFrame(value);
				logResult(rowin);
				writeResult(8, rowin, "P");			
				String script = appClass+"."+"switchToFrame(\""+value+"\");";
				writeScript(9, rowin, script);
			}
			else if (action.equals("runTestCase")){
				log("Run the \""+value+"\" test case.");
				runTestCase(value);				
				logResult(rowin);
				writeResult(8, rowin, "P");
				String script = appClass+"."+"runTestCase(\""+value+"\");";
				writeScript(9, rowin, script);
			}



			else{
				log("Can not run the action");

			}

		}
   }

	

}
