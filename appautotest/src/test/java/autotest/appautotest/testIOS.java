package autotest.appautotest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import initial.ticketIOS;
public class testIOS {
	ticketIOS app= new ticketIOS();
	
	@BeforeMethod
	public void setUp(){
		app.initialTestData();
		

		app.runIOSApp();;


	}

    @AfterMethod
    public void tearDown(){

    	app.quit();
    }
    
    @Test
    public void login(){
//    	app.waitDisplay("启动页", "略过");
//    	app.clickElement("启动页", "略过");
//    	app.waitDisplay("启动页", "关闭广告");
//    	CommonTools.sleep(1000);
//    	app.clickElement("启动页", "关闭广告");
//
//    	app.waitDisplay("首页", "菜单");
//    	CommonTools.sleep(1000);
//    	app.clickElement("首页", "菜单");
//    	
//    	app.waitDisplay("菜单", "爆料");
//    	CommonTools.sleep(1000);
//    	app.clickElement("菜单", "爆料");
//    	CommonTools.sleep(10000);

    	app.runTestCase("进入首页");

    }
    
}
