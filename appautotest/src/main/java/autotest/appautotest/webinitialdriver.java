package autotest.appautotest;




import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.apache.commons.io.FileUtils;

import java.io.IOException;


public class webinitialdriver {
	public static WebDriver driver;	
	public WebDriverWait wait; 
	static CommonTools tool = new CommonTools();
	
	public static void takescreenshot() throws IOException{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String dir_name = tool.setCurrentPath("\\screenshot\\");
	  	if (!(new File(dir_name).isDirectory())) {  // 判断是否存在该目录
	  		new File(dir_name).mkdir();  // 如果不存在则新建一个目录
	  	}
		FileUtils.copyFile(scrFile, new File(dir_name+tool.getCurrentTime()+".jpg"));
		
	
	}
	

    
    

}
