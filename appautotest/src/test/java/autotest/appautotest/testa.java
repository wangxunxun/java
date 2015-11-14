package autotest.appautotest;







import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import initial.ticketAndroid;


@Listeners(utils.TestngListener.class)
public class testa{
	ticketAndroid ticketApp = new ticketAndroid();
	@BeforeMethod
	public void setUp(){
		ticketApp.logClassInfo("34334");
		ticketApp.initialTestData();
		
		ticketApp.runAndroidApp();

	}

    @AfterMethod
    public void tearDown(){

    	ticketApp.quit();
    }
    
    
    @Test
    public void enterMainPage(){
    	ticketApp.logTestDescription("3433443");
    	ticketApp.sleep(5000);
    	ticketApp.swipeOfType("left");
    	ticketApp.sleep(500);
    	ticketApp.swipeOfType("left");
    	ticketApp.logSuccessMessage("343434");
    	ticketApp.getScreen();
    	ticketApp.getElementScreen("启动页", "开启直达车");
    	ticketApp.clickElement("启动页", "开启直达车");
//    	ticketApp.assertEquals("343", "444");
    	
    }
    

//    @Test
    public void login(){


    	ticketApp.runTestCase("登录");



    }


    
}