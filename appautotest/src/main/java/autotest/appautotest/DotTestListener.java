package autotest.appautotest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import autotest.appautotest.ScreenShot;

public class DotTestListener extends TestListenerAdapter {
	 
	CommonTools tool = new CommonTools();

	@Override
    public void onTestFailure(ITestResult tr) {    
        try {
            webtest tb = (webtest) tr.getInstance();
            WebDriver driver = tb.getDriver();              
            tool.log(tr.getName() + " Failure");
            ScreenShot shot = new ScreenShot(driver,tr);
            shot.takescreenshot();

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {         
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


}
 

