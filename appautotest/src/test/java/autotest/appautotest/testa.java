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
//    	this.enterMainPage();
    	ticketApp.tool.sleep(10000);
    	ticketApp.runTestCase(testCaseData, "test1", eledata);
/*    	ticketApp.clickElement(eledata, "更多", "帮助中心");
    	ticketApp.clickElement(eledata, "标题栏", "返回");
    	ticketApp.clickElement(eledata, "更多", "免责声明");
    	ticketApp.clickElement(eledata, "标题栏", "返回");
    	ticketApp.clickElement(eledata, "更多", "关于我们");
    	ticketApp.clickElement(eledata, "标题栏", "返回");
    	ticketApp.clickElement(eledata, "更多", "版本更新");   
    	String a = ticketApp.getElementText(eledata, "更多", "帮助中心");
    	ticketApp.assertEquals(a, "帮助中心");
    	ticketApp.tool.log(a);
    	ticketApp.clickElement(eledata, "首页", "登录");
    	ticketApp.sendKeys(eledata, "登录", "账户", "18627802681");
    	ticketApp.sendKeys(eledata, "登录", "密码", "18627802681");
    	ticketApp.clickElement(eledata, "登录", "普通登录按钮");*/
    	

    }


    
}