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
	public static void takescreenshot() throws IOException{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String dir_name = CommonTools.getCurrentPath("\\screenshot\\");
	  	if (!(new File(dir_name).isDirectory())) {  // 判断是否存在该目录
	  		new File(dir_name).mkdir();  // 如果不存在则新建一个目录
	  	}
		FileUtils.copyFile(scrFile, new File(dir_name+CommonTools.getCurrentTime()+".jpg"));
		
	
	}
	
    protected void log(String content, Integer type) {

        switch (type) {
        case 1: {
            System.out.println(CommonTools.getCurrentTime() + " INFO - " + content);
            break;
        }
        case 2: {
            System.err.println(CommonTools.getCurrentTime() + " ERROR - " + content);
            break;
        }
        case 3: {
            System.out.println(CommonTools.getCurrentTime() + " WARNING - " + content);
            break;
        }
        case 4: {
            System.err.println(CommonTools.getCurrentTime() + " WARNING - " + content);
            break;
        }
       }

    }
	
    public void log(String content) {

        log(content, 1);
    }
    
    public void waitByTimeOut(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    

}
