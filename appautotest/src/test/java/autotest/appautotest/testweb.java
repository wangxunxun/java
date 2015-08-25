package autotest.appautotest;
import java.io.IOException;
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


import base.WebApp;

public class testweb extends WebApp {
	static CommonTools tool = new CommonTools();
	static ReadTestData readtestdata = new ReadTestData();
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
    	String url="http://www.baidu.com";
		enterUrl(url);
		driver.findElement(By.cssSelector("#lh > a:nth-child(3)")).click();
		List<Map<String, String>> data = readtestdata.getTestData("testresource\\test.xls", "Sheet2");
		System.out.println(data);
		tool.log("4545");
		Assert.assertEquals("444", "5555");
//		Assert.assertEquals(driver.findElement(By.cssSelector("#nav > li.li6 > a")).getText(), "联系我们");
		Thread.sleep(5000);
		
    }
}
