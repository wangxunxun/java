package autotest.appautotest;


import io.appium.java_client.android.AndroidDriver;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.AndroidApp;

public class testand extends AndroidApp{
	static String excelpath = "testresource\\test.xls";
	static String sheettestapp = "testapp";
	static String zhuce1 = "注册";
	private Map<String, Map<String, Map<String, String>>> eledata = initialeledata(excelpath, sheettestapp);
	List<Map<String, String>> zhuce = getTestData(excelpath, zhuce1);
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
        wait = new WebDriverWait(driver,20);

        
    }
 
    @AfterTest
    public void tearDown() throws Exception {
    	quit();
    }
    
    @Test
    public void test() throws InterruptedException, IOException{
    	getButtons();
    	tool.sleep(2000);    	
/*    	clickElement(eledata, "首页", "about");
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
    	for(Map<String, String> data : zhuce){
    		sendKeys(eledata, "edittext", "昵称", data.get("会员昵称"));
    		clickBack();
    		waitDisplay(eledata, "首页", "edittext");
    		clickElement(eledata, "首页", "edittext");
    	}
    	clickBack();
    	tool.sleep(5000);*/
/*    	clickElement(eledata, "首页", "checkbox");
    	clickElement(eledata, "checkbox页", "体育");
    	clickElement(eledata, "checkbox页", "提交");
    	clickBack();
    	tool.sleep(1000);
    	swipeOfType("up");
    	swipeOfType("up");
    	clickElement(eledata, "首页", "spinner");
    	clickElement(eledata, "spinner页", "spinner");
    	clickElement(eledata, "spinner页", "工作证");
    	clickBack();*/
    	swipeOfType("up");
    	swipeOfType("up");
    	swipeOfType("up");
    	swipeOfType("up");
    	System.out.println(driver.manage().window().getSize().getWidth());
    	System.out.println(driver.manage().window().getSize().getHeight());
//    	scrollToClick("图片浏览");
    	tool.sleep(2000);
//    	clickElement(eledata, "首页", "seekbar");
//    	clickElement(eledata, "首页", "tabhost");
//    	tool.sleep(5000);
//    	clickElement(eledata, "tabhost", "已接来电");
//    	clickElement(eledata, "首页", "gridview");
//    	clickElement(eledata, "gridview", "林间小路");
    	clickElement(eledata, "首页", "alertdialog");
    	tool.sleep(1000);
    	clickElement(eledata, "alertdialog", "多选列表");
    	clickElement(eledata, "alertdialog", "超级玛丽");
    	clickCoordinate(eledata, "alertdialog", "确定c");
//    	longTab(360, 976);
//    	swipe(360, 226, 500, 226, 1000); 
//    	driver.tap(1, 360, 976, 1000);
//    	clickElement(eledata, "alertdialog", "确定");
    	
//		driver.findElementByClassName("android.widget.SeekBar").swipe(SwipeElementDirection.RIGHT, 20000);
    	
//    	waitDisplay(eledata, "首页", "seekbar");
//    	clickElement(eledata, "首页", "seekbar");
    	
    	tool.sleep(5000);
    	
//    	assertEquals("77", "333");


    }
}
