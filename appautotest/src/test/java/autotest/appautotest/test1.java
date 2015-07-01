package autotest.appautotest;




import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import autotest.appautotest.appinitialdriver;

public class test1 extends andoridtest {

			
    @Test
    public void addContact() throws InterruptedException{
    	CommonTools.waitByTimeOut(2000);
        driver.getScreenshotAs(OutputType.BASE64);

    	gettextviews();
    	Assert.assertEquals("特别提示", returnelebyclassname("android.widget.TextView", 1).getText());
    	returnelebyclassname("android.widget.Button", 2).click();
    	Thread.sleep(5000);
    	swipeOfType("left");
        System.out.println("left");
        driver.getScreenshotAs(OutputType.FILE);
    	Thread.sleep(5000);
        driver.swipe(400, 500, 50, 500, 100);
        gettextviews();
        getbuttons();
        getimagebuttons();
        getimageviews();
        getedittexts();
        returnelebyclassname("android.widget.ImageButton", 1).click();
//        driver.findElementById("com.autonavi.xmgd.navigator:id/guide_btn_end").click();
  

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.autonavi.xmgd.navigator:id/guide_btn_end")));
        System.out.println("ok");

    }

}
