package testTicketWeb;

import initial.piaoWuWebTest;

import java.io.IOException;

import jxl.read.biff.BiffException;


import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class qiChe {
	piaoWuWebTest piaoWuWebApp = new piaoWuWebTest();
	String addqichezhan = "添加汽车车站";

	
	String url="http://106.185.47.124:5000/Server/Auth/Login?next=%2F";

	@DataProvider(name="addqichezhan")
	public Object[][] dataProvider1(){		
		return piaoWuWebApp.getTestDataForTestNG(addqichezhan);
	}
	

	@BeforeClass
	public void setUp(){
		piaoWuWebApp.initialTestData();
		piaoWuWebApp.runChormeApp();				
	}


		
	@AfterClass
	public void tearDown() {
		piaoWuWebApp.quit();
	}
	@Test(dataProvider="addqichezhan")
    public void addQiCheZhan(String address,String name,String pinyin,String longitude,String latitude,String bashi,String phone,String from,String to,String city) throws InterruptedException, BiffException, IOException{
		
		piaoWuWebApp.enterHomePage(url);
		piaoWuWebApp.clickElement("侧边栏", "长途售票管理");
		piaoWuWebApp.clickElement("侧边栏", "汽车车站维护");
		piaoWuWebApp.waitDisplay("汽车车站维护", "添加车站");
		piaoWuWebApp.clickElement("汽车车站维护", "添加车站");

		piaoWuWebApp.switchToFrame("xubox_iframe1");


		piaoWuWebApp.sendKeys("汽车-添加车站", "车站地址",address); 
		piaoWuWebApp.sendKeys("汽车-添加车站", "车站名称",name); 
		piaoWuWebApp.sendKeys("汽车-添加车站", "拼音",pinyin); 
		piaoWuWebApp.sendKeys("汽车-添加车站", "经度",longitude); 
		piaoWuWebApp.sendKeys("汽车-添加车站", "纬度",latitude); 
		piaoWuWebApp.clear("汽车-添加车站", "途径巴士");
		piaoWuWebApp.sendKeys("汽车-添加车站", "途径巴士",bashi); 
		piaoWuWebApp.sendKeys("汽车-添加车站", "车站电话",phone); 

		piaoWuWebApp.sendKeys("汽车-添加车站", "营业时间从",from); 
		piaoWuWebApp.sendKeys("汽车-添加车站", "营业时间到",to); 
		piaoWuWebApp.sendKeys("汽车-添加车站", "车站所在城市",city); 
		piaoWuWebApp.clickElement("汽车-添加车站", "车站地址");
		piaoWuWebApp.clickElement("汽车-添加车站", "提交");
		piaoWuWebApp.tool.sleep(1000);



    }




	
}
