package autotest.appautotest;




import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;



public class test1 extends testandroid {

			
    @Test
    public void addContact() throws InterruptedException, IOException{
    	tool.waitByTimeOut(8000);   
    	swipeOfType("left");
    	tool.waitByTimeOut(1000);
    	swipeOfType("left");
    	returnelebyclassname("ImageView", 1).click();
    	tool.waitByTimeOut(2000);
    	returnelebyclassname("TextView", 2).click();
    	tool.waitByTimeOut(2000);
    	swipeOfType("down");
    	returnelebyclassname("ImageView", 0).click();
    	clickById("com.tencent.news:id/user_center_search");    	
    	tool.waitByTimeOut(2000);    	
    	inputById("共和国", "com.tencent.news:id/inputSearch");
    	tool.waitByTimeOut(2000);

    	clickmenu();
    	




    	

    	
    	tool.waitByTimeOut(2000);





//        returnelebyclassname("android.widget.ImageButton", 1).click();
//        driver.findElementById("com.autonavi.xmgd.navigator:id/guide_btn_end").click();
  
        tool.waitByTimeOut(3000);
        
        waitDisplayById("com.autonavi.xmgd.navigator:id/guide_btn_end");
        
        System.out.println("ok");

    }

}
