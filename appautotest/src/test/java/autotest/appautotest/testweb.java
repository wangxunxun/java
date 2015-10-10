package autotest.appautotest;
import java.io.IOException;


import java.util.Map;

import jxl.read.biff.BiffException;


import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.ReadTestData;

import base.WebApp;
import initial.piaoWuWebTest;
public class testweb extends WebApp {
	piaoWuWebTest piaoWuWebApp = new piaoWuWebTest();
	String excelpath = "testresource\\test.xls";
	String sheet = "12306";
	String testSheet = "注册";
	ReadTestData dd = new ReadTestData();

	@DataProvider(name="dataprovider1")
	public Object[][] dataProvider1(){		
		return piaoWuWebApp.getTestDataForTestNG(excelpath, sheet);
	}
	
	@BeforeTest
	public void setUp(){
		piaoWuWebApp.initialTestData();
		piaoWuWebApp.runChormeApp();				
	}


		
	@AfterTest	
	public void tearDown() {
		quit();
	}

	@Test
    public void test() throws InterruptedException, BiffException, IOException{
//    	String url="http://wh.58.com";
//    	String url="http://baidu.com";
		String url="http://120.24.255.213:5000/Server/Auth/Login?next=%2F";
		piaoWuWebApp.tool.sleep(2000);
		piaoWuWebApp.get(url);
		piaoWuWebApp.sendKeys("登录页", "登录输入框", "admin");
		piaoWuWebApp.sendKeys("登录页", "密码输入框", "admin");
		piaoWuWebApp.clickElement("登录页", "登录");
		piaoWuWebApp.clickElement("侧边栏", "长途售票管理");
		piaoWuWebApp.clickElement("侧边栏", "汽车车站维护");
		piaoWuWebApp.clickElement("汽车车站维护", "添加车站");
		piaoWuWebApp.sendKeys("添加车站", "车站地址","eeee"); 
		piaoWuWebApp.tool.sleep(20000);



    }
}
