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
	protected String testExcelPath = "testresource\\test.xls";;
	protected String elementSheet = "票务系统";
	protected String testCaseSheet = "票务系统测试用例";
	protected String testDataSheet = null;
	
	private Map<String, Map<String, Map<String, String>>> elementData = ticketApp.getElementData(testExcelPath, elementSheet);
	private Map<String, Object> testCaseData = ticketApp.getTestCaseData(testExcelPath, testCaseSheet);
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
    	ticketApp.runTestCase(testCaseData, elementData,"登录");

    	

    }


    
}