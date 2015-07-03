package autotest.appautotest;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ScreenShot {
    public WebDriver driver;
    public ITestResult tr;
    CommonTools tool = new CommonTools();
 
    public ScreenShot(WebDriver driver,ITestResult tr) {
        this.driver = driver;
        this.tr = tr;
    }
  
	public void takescreenshot() throws IOException, InterruptedException{
		Thread.sleep(3000);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String dir_name = tool.setCurrentPath("\\screenshot\\");
	  	if (!(new File(dir_name).isDirectory())) {  // 判断是否存在该目录
	  		new File(dir_name).mkdir();  // 如果不存在则新建一个目录
	  	}
	  	String filepath = dir_name+tool.getCurrentTime()+ "_"+ tr.getName()+  ".jpg";
		FileUtils.copyFile(scrFile, new File(filepath));
		Reporter.setCurrentTestResult(tr);
		Reporter.log(filepath);
		Reporter.log("<img src=\"../" + filepath + "\"/>");
		
	
	}
}