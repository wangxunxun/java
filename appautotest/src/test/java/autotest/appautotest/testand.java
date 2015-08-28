package autotest.appautotest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.AndroidApp;

public class testand extends AndroidApp{
	static String excelpath = "testresource\\test.xls";
	static String sheet = "testapp";
	private Map<String, Map<String, Map<String, String>>> eledata = initialeledata(excelpath, sheet);
	@BeforeTest
    public void setUp() throws Exception {
        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/testresource/apps");
        File app = new File(appDir, "MainActivity.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName","XiaoMi2");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        capabilities.setCapability("appPackage", "com.example.testandroid");
        capabilities.setCapability("appActivity", "com.example.testandroid.MainActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        wait = new WebDriverWait(driver,10);
        
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
    }
 
    @AfterTest
    public void tearDown() throws Exception {
    	quit();
    }
    
    @Test
    public void test() throws InterruptedException, IOException{
    	getButtons();
    	tool.sleep(2000);    	
    	clickElement(eledata, "首页", "about");
    	clickBack();
    	waitDisplay(eledata, "首页", "layout");
    	isDisplayed(eledata, "首页", "layout");
    	String a =getElementText(eledata, "首页", "layout");
    	tool.log(a);
    	clickElement(eledata, "首页", "layout");
    	clickBack();
    	clickElement(eledata, "首页", "textview");
    	clickBack();
    	clickElement(eledata, "首页", "edittext");
    	sendKeys(eledata, "edittext", "昵称", "好样");
    	tool.sleep(5000);
    	clickBack();
//    	assertEquals("77", "333");


    }
}
