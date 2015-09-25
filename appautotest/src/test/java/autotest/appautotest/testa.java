package autotest.appautotest;




import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import initial.ticketAndroid;


public class testa{
	ticketAndroid ticketApp = new ticketAndroid();



	private Map<String, Map<String, Map<String, String>>> eledata = ticketApp.eledata;
	private Map<String, Object> testCaseData = ticketApp.testData;
	@BeforeMethod
	public void setUp() throws Exception{
		ticketApp.runApp();
	}

    @AfterMethod
    public void tearDown() throws Exception {

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
    	ticketApp.runTestCase(testCaseData, eledata,"test2");

    	

    }


    
}