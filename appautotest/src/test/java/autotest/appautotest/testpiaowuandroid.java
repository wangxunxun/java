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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.AndroidApp;
public class testpiaowuandroid extends AndroidApp{
	static String excelpath = "testresource\\test.xls";
	static String elesheet = "票务系统";
//	static String zhuce1 = "注册";
	private Map<String, Map<String, Map<String, String>>> eledata = initialeledata(excelpath, elesheet);
//	List<Map<String, String>> zhuce = getTestData(excelpath, zhuce1);
	@BeforeMethod
	public void test() throws Exception{
		setUp("ticketingsystem.apk","cn.beyondsoft.wicket","cn.beyondsoft.wicket.LoddingActivity");
	}
/*    public void setUp() throws Exception {
        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/testresource/apps");
        File app = new File(appDir, "ticketingsystem.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName","XiaoMi2");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        capabilities.setCapability("appPackage", "cn.beyondsoft.wicket");
        capabilities.setCapability("appActivity", "cn.beyondsoft.wicket.LoddingActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        wait = new WebDriverWait(driver,20);
        
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
    }
 */
    @AfterMethod
    public void tearDown() throws Exception {

    	quit();
    }
    
    
    @Test
    public void enterMainPage() throws InterruptedException, IOException{

    	tool.sleep(10000);
    	swipeOfType("left");
    	tool.sleep(500);
    	swipeOfType("left");
    	clickElement(eledata, "启动页", "开启直达车");
    	waitDisplay(eledata, "首页", "汽车票");
    	tool.sleep(1000);
    }
    @Test
    public void login() throws InterruptedException, IOException{
    	enterMainPage();
    	clickElement(eledata, "首页", "登录");
    	waitDisplay(eledata, "登录", "普通登录");
    	sendKeys(eledata, "登录", "账户", "18627802681");
    	sendKeys(eledata, "登录", "密码", "123456");
    	clickElement(eledata, "登录", "登录");

    }
    
    @Test
    public void searchzz() throws InterruptedException, IOException{
    	enterMainPage();
    	clickElement(eledata, "汽车票", "查询");


    }
    
}