package utils;



import java.io.File;
import java.io.IOException;




import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;




import utils.CommonTools;
import base.WebApp;
public class TestngListenerWeb extends TestListenerAdapter {

	CommonTools tool = new CommonTools();

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		CommonTools.log(tr.getName() + " Failure");
		try {
			takeScreenShot(tr);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void takeScreenShot(ITestResult tr) throws InterruptedException, IOException {
		Thread.sleep(3000);
		File scrFile = ((TakesScreenshot) WebApp.driver).getScreenshotAs(OutputType.FILE);		
		String dir_name = CommonTools.setPath("/failTestCaseScreenShot/");
	  	if (!(new File(dir_name).isDirectory())) {  // 判断是否存在该目录
	  		new File(dir_name).mkdir();  // 如果不存在则新建一个目录
	  	}
	  	String filepath = dir_name+CommonTools.getCurrentTime()+ "_"+ tr.getName()+  ".jpg";
		FileUtils.copyFile(scrFile, new File(filepath));
		Reporter.setCurrentTestResult(tr);
		Reporter.log(filepath);
		Reporter.log("<img src=\"../" + filepath + "\"/>");


	}

}
