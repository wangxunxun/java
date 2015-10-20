package base;




import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import core.UI;

public class IOSApp extends UI {

	public static IOSDriver<IOSElement> driver;

  	public void setUp(){
		    // set up appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
    
        capabilities.setCapability("app", "nmi.hk.com.nextmedia.magazine.nextmediaplus.qa");
        capabilities.setCapability("deviceName", "iPhone Simulator");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("platform", "MAC");
        capabilities.setCapability("platformName", "iOS");

        capabilities.setCapability("browserName", "");

        try {
			driver = new IOSDriver<IOSElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        wait = new WebDriverWait(driver,waitTime);
        elementData = getElementData();

    	testCaseData = getTestCaseData();
  	}
  	public void quit(){
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
}
