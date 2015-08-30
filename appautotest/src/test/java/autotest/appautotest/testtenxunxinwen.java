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
public class testtenxunxinwen extends AndroidApp{
	static String excelpath = "testresource\\test.xls";
	static String elesheet = "腾讯新闻";
//	static String zhuce1 = "注册";
	private Map<String, Map<String, Map<String, String>>> eledata = initialeledata(excelpath, elesheet);
//	List<Map<String, String>> zhuce = getTestData(excelpath, zhuce1);
	@BeforeTest
    public void setUp() throws Exception {
        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/testresource/apps");
        File app = new File(appDir, "com.tencent.news_054632.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName","XiaoMi2");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        capabilities.setCapability("appPackage", "com.tencent.news");
        capabilities.setCapability("appActivity", "com.tencent.news.activity.SplashActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        wait = new WebDriverWait(driver,20);
        
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
    }
 
    @AfterTest
    public void tearDown() throws Exception {
    	quit();
    }
    
    @Test
    public void test() throws InterruptedException, IOException{
    	waitDisplay(eledata, "广告页", "第一张");
    	swipeOfType("left");
//    	tool.sleep(20000);  
    	getImageViews();
    	clickElement(eledata, "广告页", "立即体验");
//    	findElementByClassNameIndex("ImageView", 1).click();

    	tool.sleep(2000);    
    	swipeOfType("down");
    	swipe(500, 100, 200, 100, 1000);
//    	clickElement(eledata, "新闻", "民生");
//    	tab(578, 100);
//    	clickElement(eledata, "新闻", "娱乐");
//    	clickElement(eledata, "新闻", "汽车");
    	tool.sleep(2000);    
    	tab(579, 100);
    	waitDisplay(eledata, "新闻详情", "分享");
    	tab(360, 500);
    	tool.sleep(500);
    	clickElement(eledata, "新闻详情", "分享");
    	clickElement(eledata, "新闻详情", "收藏");
    	waitDisplay(eledata, "新闻详情", "用户");

    	sendKeys(eledata, "新闻详情", "用户","59853844");
    	sendKeys(eledata, "新闻详情", "密码","xunlovehua");
    	clickElement(eledata, "新闻详情", "登录");
    	waitDisplay(eledata, "新闻详情", "分享");
    	clickElement(eledata, "新闻详情", "返回");
    	clickElement(eledata, "公共", "头像");
    	clickElement(eledata, "左侧栏", "更多");
    	clickElement(eledata, "设置", "正文字号");
    	clickElement(eledata, "设置", "特大");
    	clickElement(eledata, "设置", "应用推荐");
//    	clickElement(eledata, "左侧栏", "搜索");
//    	sendKeys(eledata, "搜索", "搜索输入框","成龙");
//    	clickEnter();
    	
    	tool.sleep(3000);
//    	clickElement(eledata, "首页", "about");
    }

}
