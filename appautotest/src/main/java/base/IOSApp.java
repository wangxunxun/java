package base;





import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;


import org.openqa.selenium.By;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import utils.CommonTools;
import utils.OperateExcel;
import utils.TestngListener;
import core.UI;

public class IOSApp extends UI {

	public static IOSDriver<IOSElement> driver;

  	public void runIOSApp(){
		    // set up appium

  		try {
			initialIOSData();
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BiffException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        DesiredCapabilities capabilities = new DesiredCapabilities();
    
        capabilities.setCapability("app", app);
        capabilities.setCapability("deviceName", iosDeviceName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("platform", platform);
        capabilities.setCapability("platformName", platformName);

        capabilities.setCapability("browserName", browserName);

        try {
			driver = new IOSDriver<IOSElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        wait = new WebDriverWait(driver,waitTime);

  	}
  	public void quit(){

    	try{
			OperateExcel testSummaySheet = new OperateExcel(testReportDir + testReportName + ".xls", testSummarySheetName);
			testSummaySheet.setFormat(10, true);
			testSummaySheet.writeTestSummaryToExcel(TestngListener.classData);
			testSummaySheet.close();
			TestngListener.classData.clear();
			
			
			OperateExcel testClassSheet = new OperateExcel(testReportDir + testReportName + ".xls", testClassName);
			testClassSheet.setColumnView(1, 100);
			testClassSheet.setColumnView(0, 40);
			testClassSheet.setFormat(10, true);
			testClassSheet.setHyperLinkForSheet(0, 0, "Back", testSummarySheetName, 0, 0);
			testClassSheet.writeLogToExcel(logData);
			testClassSheet.close();
			logData.clear();
    		
    		
			String excelPath = CommonTools.setPath(testDataExcelPath);
			if(writeResult==true){
				CommonTools.writeResultToExcel(excelPath, testCaseSheet, testResultData);
				testResultData.clear();
			}
			if(writeScript == true){
				CommonTools.writeScriptToExcel(excelPath, testCaseSheet, testScriptData);
				testScriptData.clear();
			}
    	}
    	catch(Exception e){
    		System.err.println(e);
    	}
    	driver.quit();
	}
  	
	public IOSElement findElement(String page,String name){

		String selecttype = elementData.get(page).get(name).get("SelectType");
		String location = elementData.get(page).get(name).get("Location");
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
		else if (selecttype.equals("UIA")){
			return driver.findElementByIosUIAutomation(location);
		}

		else if (selecttype.equals("linktext")){
			return driver.findElement(By.linkText(location));
		}
		else if (selecttype.equals("partiallinktext")){
			return driver.findElement(By.partialLinkText(location));
		}
		else if (selecttype.equals("tagname")){
			return driver.findElement(By.tagName(location));
		}
		else if (selecttype.equals("scrollname")){
			return driver.scrollTo(location);
		}
	
		else{
			System.out.println("Can not find the element.");
		}
		return null;
	}
	
    public void swipeOfType(String type) {
        int windowlenX = driver.manage().window().getSize().getWidth();
        int windowlenY = driver.manage().window().getSize().getHeight();
        String swipeLeft = "left";
        String swipeLeftSide = "leftSide";
        String swipeRight = "right";
        String swipeRightSide = "rightSide";
        String swipeUp = "up";
        String swipeTop = "top";
        String swipeDown = "down";
        String swipeBottom = "bottom";

        // Sliding screen to the left
        if (type.equalsIgnoreCase(swipeLeft)) {
            driver.swipe((int) (windowlenX * 0.9), (int) (windowlenY * 0.5), (int) (windowlenX * 0.2), (int) (windowlenY * 0.5),
                    3000);
        }

        // From the left of screen to began to slip
        if (type.equalsIgnoreCase(swipeLeftSide)) {

            driver.swipe(1, (int) (windowlenY * 0.5), (int) (windowlenX * 0.9), (int) (windowlenY * 0.5), 3000);
        }

        // Sliding screen to the right
        if (type.equalsIgnoreCase(swipeRight)) {

            driver.swipe((int) (windowlenX * 0.2), (int) (windowlenY * 0.5), (int) (windowlenX * 0.9), (int) (windowlenY * 0.5),
                    3000);
        }

        // From the right of screen to began to slip
        if (type.equalsIgnoreCase(swipeRightSide)) {

            driver.swipe((int) (windowlenX * 0.9), (int) (windowlenY * 0.5), (int) (windowlenX * 0.2), (int) (windowlenY * 0.5),
                    3000);
        }

        // Screen upward sliding
        if (type.equalsIgnoreCase(swipeUp)) {

            driver.swipe((int) (windowlenX * 0.5), (int) (windowlenY * 0.9), (int) (windowlenX * 0.5), (int) (windowlenY * 0.4),
                    3000);
        }

        // From the top of screen to began to slip
        if (type.equalsIgnoreCase(swipeTop)) {

            driver.swipe((int) (windowlenX * 0.5), 0, (int) (windowlenX * 0.5), (int) (windowlenY * 0.8), 3000);
        }

        // Slide down the screen
        if (type.equalsIgnoreCase(swipeDown)) {

            driver.swipe((int) (windowlenX * 0.5), (int) (windowlenY * 0.4), (int) (windowlenX * 0.5), (int) (windowlenY * 0.9),
                    3000);
        }

        // From the bottom of screen to began to slip
        if (type.equalsIgnoreCase(swipeBottom)) {

            driver.swipe((int) (windowlenX * 0.5), (int)(windowlenY * 0.9), (int) (windowlenX * 0.5), (int) (windowlenY * 0.1), 3000);
        }

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
					putResultData(rowin, "P");
					String script = appClass+"."+"clickElement(\""+page+"\",\""+name+"\");";
					putScriptData(rowin, script);
		
				}
				else if (action.equals("sleep")){
					int v=Integer.parseInt(value);
					CommonTools.sleep(v);
					logResult(rowin);
					putResultData(rowin, "P");
					String script = "CommonTools.sleep("+v+");";
					putScriptData(rowin, script);
		
				}
				else if (action.equals("waitDisplay")){
					waitDisplay(page, name);
					logResult(rowin);
					putResultData(rowin, "P");
					String script = appClass+"."+"waitDisplay(\""+page+"\",\""+name+"\");";
					putScriptData(rowin, script);
		
				}
		
		
				else if (action.equals("clear")){
					clear(page, name);
					logResult(rowin);
					putResultData(rowin, "P");
					String script = appClass+"."+"clear(\""+page+"\",\""+name+"\");";
					putScriptData(rowin, script);
				}
		
		
		
				else if (action.equals("swipeOfType")){
					swipeOfType(value);
					logResult(rowin);
					putResultData(rowin, "P");
					String script = appClass+"."+"swipeOfType(\""+value+"\");";
					putScriptData(rowin, script);
		
				}
				else if (action.equals("sendKey")){
					sendKeys(page, name, value);
					logResult(rowin);
					putResultData(rowin, "P");
					String script = appClass+"."+"sendKeys(\""+page+"\",\""+name+"\",\""+value+"\");";
					putScriptData(rowin, script);
		
				}
				else if (action.equals("assert")){
					actual = getElementText(page, name);
					assertEquals(actual, expected);
					logResult(rowin);
					putResultData(rowin, "P");
					String script = appClass+"."+"assertEquals("+appClass+"."+"getElementText(\""+page+"\",\""+ name+"\")"+","+"\""+expected+"\");";
					putScriptData(rowin, script);
		
					
				}
				else if (action.equals("runTestCase")){
					runTestCase(value);
					logResult(rowin);
					putResultData(rowin, "P");
					String script = appClass+"."+"runTestCase(\""+value+"\");";
					putScriptData(rowin, script);
		
				}
		
		
				else{
					CommonTools.log("Can not run the action");
		
				}
		
			}
	   }
}
