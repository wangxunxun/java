package autotest.appautotest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class webtest extends webinitialdriver{
	public static WebDriver driver;	
	
    public WebDriver getDriver() {
        return driver;
    }
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
