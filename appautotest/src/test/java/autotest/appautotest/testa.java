package autotest.appautotest;







import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import initial.ticketAndroid;



public class testa{
	ticketAndroid ticketApp = new ticketAndroid();
	@BeforeMethod
	public void setUp(){
		ticketApp.initialTestData();
		
		ticketApp.runAndroidApp();
		ticketApp.setTestClassName(this.getClass().getName());
		ticketApp.log("Start to run "+this.getClass().getName()+".");
	}

    @AfterMethod
    public void tearDown(){

    	ticketApp.quit();
    }
    
    
    @Test
    public void enterMainPage(){

    	ticketApp.sleep(5000);
    	ticketApp.swipeOfType("left");
    	ticketApp.sleep(500);
    	ticketApp.swipeOfType("left");
    	ticketApp.getElementScreen("启动页", "开启直达车");
    	ticketApp.clickElement("启动页", "开启直达车");
    	
    }

//    @Test
    public void login(){


    	ticketApp.runTestCase("登录");



    }


    
}