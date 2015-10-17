package autotest.appautotest;






import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import initial.ticketAndroid;
import utils.CommonTools;


public class testa{
	ticketAndroid ticketApp = new ticketAndroid();
	@BeforeMethod
	public void setUp(){
		ticketApp.initialTestData();
		
		ticketApp.runAndroidApp();

	}

    @AfterMethod
    public void tearDown(){

    	ticketApp.quit();
    }
    
    
/*    @Test
    public void enterMainPage(){

    	ticketApp.tool.sleep(10000);
    	ticketApp.swipeOfType("left");
    	ticketApp.tool.sleep(500);
    	ticketApp.swipeOfType("left");
    	ticketApp.clickElement(eledata, "启动页", "开启直达车");
    	ticketApp.waitDisplay(eledata, "首页", "汽车票");
    	ticketApp.tool.sleep(1000);
    }*/

    @Test
    public void login(){

    	ticketApp.runTestCase("登录");


    }


    
}