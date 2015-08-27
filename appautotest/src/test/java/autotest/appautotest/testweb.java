package autotest.appautotest;
import java.io.IOException;

import utils.ReadElementData;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.CommonTools;
import utils.ReadTestData;
import utils.ReadElementData;


import base.WebApp;

public class testweb extends WebApp {
	static CommonTools tool = new CommonTools();
	static String excelpath = "testresource\\test.xls";
	static String sheet = "58同城";
	private Map<String, Map<String, Map<String, String>>> eledata = initialeledata(excelpath, sheet);
	
	
	@BeforeTest
	public void setUp(){
		//设置 Chrome的路径
		String dirs=tool.setCurrentPath("/testresource/chromedriver.exe");

		System.setProperty("webdriver.chrome.driver", 
				dirs);
		
		driver=new ChromeDriver();
		wait = new WebDriverWait(driver,20);
	}


		
	@AfterTest
	
	public void tearDown() {
		// TODO Auto-generated method stub
		driver.quit();
	}
	@Test
	
    public void test() throws InterruptedException, BiffException, IOException{
    	String url="http://wh.58.com";
//    	String url="http://69.164.202.55/testlink/login.php";
    	driver.get(url);
//    	waitDisplay(eledata, "首页", "整租");
//    	findElement(eledata, "首页", "整租").click();
//    	switchToNewPage();
//    	waitDisplay(eledata, "整租页面", "搜索输入框");
//    	findElement(eledata, "整租页面", "搜索输入框").sendKeys("中建康城");
// 		waitDisplay(eledata, "整租页面", "搜本类");
//    	findElement(eledata, "整租页面", "搜本类").click();
    	waitDisplay(eledata, "首页", "免费发布信息");
    	findElement(eledata, "首页", "免费发布信息").click();
    	switchToNewPage();
    	waitDisplay(eledata, "选择大类", "免费招聘");
    	findElement(eledata, "选择大类", "免费招聘").click();
    	tool.sleep(2000);
    	waitDisplay(eledata, "选择大类", "兼职招聘");
    	findElement(eledata, "选择大类", "兼职招聘").click();
    	waitDisplay(eledata, "登录页面", "用户名");
    	findElement(eledata, "登录页面", "用户名").sendKeys("59853844@qq.com");
    	waitDisplay(eledata, "登录页面", "密码");
    	findElement(eledata, "登录页面", "密码").sendKeys("wangxun2");
    	waitDisplay(eledata, "登录页面", "登录");
    	findElement(eledata, "登录页面", "登录").click();
    	waitDisplay(eledata, "填写信息", "昵称");
    	String a = findElement(eledata, "填写信息", "昵称").getText();
    	tool.log(a);
    	checkString(a, "wangxun2012");
    	waitDisplay(eledata, "填写信息", "星期一晚上");
    	findElement(eledata, "填写信息", "星期一晚上").click();
    	tool.sleep(2000);
    	rollToBottom();
    	tool.sleep(2000);
    	setScroll(driver, 600);
//    	Assert.assertEquals(a, "wangxun20123");
    	tool.sleep(10000);

    }
}
