package autotest.appautotest;
import java.io.IOException;


import java.util.Map;

import jxl.read.biff.BiffException;


import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



import base.WebApp;

public class testweb extends WebApp {
	static String excelpath = "testresource\\test.xls";
	static String sheet = "12306";
//	private Map<String, Map<String, Map<String, String>>> eledata = getElementData(excelpath, sheet);
	
	@BeforeTest
	public void setUp(){
		//设置 Chrome的路径
		String dirs=tool.setPath("/testresource/chromedriver.exe");

		System.setProperty("webdriver.chrome.driver", dirs);
		driver=new ChromeDriver();

//		System.setProperty("webdriver.firefox.bin", "E:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");   
//		driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.ie.driver", "D:\\IEDriverServer.exe");
//		WebDriver driver = new InternetExplorerDriver();
		
		wait = new WebDriverWait(driver,20);
		
	}


		
	@AfterTest
	
	public void tearDown() {
		// TODO Auto-generated method stub
		quit();
	}
	@Test
	
    public void test() throws InterruptedException, BiffException, IOException{
//    	String url="http://wh.58.com";
//    	String url="http://baidu.com";
		String url="http://www.12306.cn/mormhweb/";
    	get(url);
/*    	tool.sleep(5000);

    	waitDisplay(eledata, "首页", "客运服务");
    	clickElement(eledata, "首页", "客运服务");
    	switchToNewWindow();
    	waitDisplay(eledata, "客运服务", "出发地");
    	clickElement(eledata, "客运服务", "出发地");
    	tool.sleep(2000);
//    	sendKeys(eledata, "客运服务", "出发地","武汉");
    	
    	KeyPress(116);
    	tool.sleep(2000);
    	waitDisplay(eledata, "客运服务", "目的地");
    	clickElement(eledata, "客运服务", "目的地");
    	tool.sleep(2000);
//    	sendKeys(eledata, "客运服务", "目的地","深圳");
    	waitDisplay(eledata, "客运服务", "查询");
    	clickElement(eledata, "客运服务", "查询");
    	tool.sleep(15000);*/
/*    	waitDisplay(eledata, "首页", "登录链接");
    	clickElement(eledata, "首页", "登录链接");
    	waitDisplay(eledata, "首页", "账号");
    	sendKeys(eledata, "首页", "账号", "qq59853844");
    	waitDisplay(eledata, "首页", "密码");
    	sendKeys(eledata, "首页", "密码", "wangxun2");

    	waitDisplay(eledata, "首页", "登录按钮");
    	clickElement(eledata, "首页", "登录按钮");
    	
    	waitDisplay(eledata, "首页", "消息");
    	
    	clickElement(eledata, "首页", "消息");
    	waitDisplay(eledata, "首页", "消息设置");
    	clickElement(eledata, "首页", "消息设置");
    	tool.sleep(5000);*/
    	
    	
    	

/*    	waitDisplay(eledata, "首页", "搜索输入框");
    	sendKeys(eledata, "首页", "搜索输入框","中建康城");
    	tool.sleep(1000);
    	tool.log(getElementValue(eledata, "首页", "搜索输入框"));
    	waitDisplay(eledata, "首页", "同城搜索");
    	tool.log(getElementValue(eledata, "首页", "同城搜索"));
    	clickElement(eledata, "首页", "同城搜索");
    	tool.sleep(3000);*/
/*    	waitDisplay(eledata, "首页", "免费发布信息");
    	clickElement(eledata, "首页", "免费发布信息");
    	
    	switchToNewWindow();
    	waitDisplay(eledata, "选择大类", "免费招聘");
    	moveMouseOn(eledata, "选择大类", "免费招聘");

    	

    	waitDisplay(eledata, "选择大类", "兼职招聘");
    	tool.sleep(1000);
    	clickElement(eledata, "选择大类", "兼职招聘");
    	
    	waitDisplay(eledata, "登录页面", "用户名");
    	sendKeys(eledata, "登录页面", "用户名", "59853844@qq.com");
    	waitDisplay(eledata, "登录页面", "密码");
    	sendKeys(eledata, "登录页面", "密码","wangxun2");
    	waitDisplay(eledata, "登录页面", "登录");
    	clickElement(eledata, "登录页面", "登录");
    	waitDisplay(eledata, "填写信息", "昵称");
    	String a = findElement(eledata, "填写信息", "昵称").getText();
    	tool.log(a);
    	checkString(a, "wangxun2012");
    	waitDisplay(eledata, "填写信息", "星期一晚上");
    	findElement(eledata, "填写信息", "星期一晚上").click();
    	tool.sleep(2000);
    	tool.sleep(2000);*/

//    	Assert.assertEquals(a, "wangxun20123");


    }
}
