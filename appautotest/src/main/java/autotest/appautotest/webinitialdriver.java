package autotest.appautotest;




import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;



public class webinitialdriver {
	public static WebDriver driver;	
	public WebDriverWait wait; 
	@BeforeTest
	public void setUp(){
		//设置 Chrome的路径
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/testresource");
        File app = new File(appDir, "chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", 
				app.getAbsolutePath());
		
		driver=new ChromeDriver();
		wait = new WebDriverWait(driver,10);
	}


		
	@AfterTest
	
	public void tearDown() {
		// TODO Auto-generated method stub
		driver.quit();
	}

		
	

}
