package autotest.appautotest;




import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;



public class test1 extends testandroid {

			
    @Test
    public void addContact() throws InterruptedException{
    	tool.waitByTimeOut(3000);   
    	swipeOfType("left");
    	tool.waitByTimeOut(1000);
    	swipeOfType("left");
    	getbuttons();
    	getimagebuttons();
    	getimageviews();
    	returnelebyclassname("ImageView", 1).click();
    	tool.waitByTimeOut(2000);
    	returnelebyclassname("TextView", 2).click();
    	tool.waitByTimeOut(2000);
    	swipeOfType("down");
    	returnelebyclassname("ImageView", 0).click();
    	clickbyid("com.tencent.news:id/user_center_search");
    	
    	
//    	clickbyid("com.tencent.news:id/inputSearch");
    	driver.findElementById("com.tencent.news:id/inputSearch").sendKeys("ererer");
    	tool.waitByTimeOut(2000);
    	tool.waitByTimeOut(2000);



        gettextviews();
        getbuttons();
        getimagebuttons();
        getimageviews();
        getedittexts();
//        returnelebyclassname("android.widget.ImageButton", 1).click();
//        driver.findElementById("com.autonavi.xmgd.navigator:id/guide_btn_end").click();
  
        tool.waitByTimeOut(3000);
        
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.autonavi.xmgd.navigator:id/guide_btn_end")));
        System.out.println("ok");

    }

}
