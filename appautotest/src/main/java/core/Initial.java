package core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import autotest.appautotest.CommonTools;
import autotest.appautotest.webinitialdriver;

public class Initial {
	public static WebDriver driver;	
	public WebDriverWait wait; 
	static CommonTools tool = new CommonTools();
	
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
}
