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
	static String sheet = "元素信息";
	private Map<String, Map<String, Map<String, String>>> eledata = initialeledata(excelpath, sheet);
	
	
	@BeforeTest
	public void setUp(){
		//设置 Chrome的路径
		String dirs=tool.setCurrentPath("/testresource/chromedriver.exe");

		System.setProperty("webdriver.chrome.driver", 
				dirs);
		
		driver=new ChromeDriver();
		wait = new WebDriverWait(driver,10);
	}


		
	@AfterTest
	
	public void tearDown() {
		// TODO Auto-generated method stub
		driver.quit();
	}
	@Test
	
    public void test() throws InterruptedException, BiffException, IOException{
    	String url="https://www.baidu.com/";
//    	String url="http://69.164.202.55/testlink/login.php";
    	driver.get(url);
    	findelement(eledata, "主页", "搜索输入框").sendKeys("33333");
    	Thread.sleep(5000);
    	findelement(eledata, "登录页", "用户名").sendKeys("admin");
    	findelement(eledata, "登录页", "密码").sendKeys("admin");
    	findelement(eledata, "登录页", "登录").click();
    	Thread.sleep(5000);
    }
}
