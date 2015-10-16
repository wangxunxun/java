package testTicketWeb;

import initial.piaoWuWebTest;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.WebApp;


public class qiChe {
	piaoWuWebTest piaoWuWebApp = new piaoWuWebTest();
	String addqichezhan = "添加汽车车站";

	
	

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
//	@Test(dataProvider="addqichezhan")
    public void test004addQiCheZhan(String address,String name,String pinyin,String longitude,String latitude,String bashi,String phone,String from,String to,String city) throws InterruptedException, BiffException, IOException{
		
		piaoWuWebApp.enterHomePage();
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

	@Test
    public void test002editQiChePiaoJia() throws InterruptedException, BiffException, IOException{
		
		


		
		String piaojia = piaoWuWebApp.qiChePiaoJia;

		String newPiaoJia = piaoWuWebApp.getTableRowLocationByCss(piaojia, 6);
		piaoWuWebApp.log(newPiaoJia);
		piaoWuWebApp.enterHomePage();
		piaoWuWebApp.clickElement("侧边栏", "长途售票管理");
		piaoWuWebApp.waitDisplay("侧边栏", "汽车线路管理");
		piaoWuWebApp.clickElement("侧边栏", "汽车线路管理");
		piaoWuWebApp.waitDisplayByCss(newPiaoJia);
		piaoWuWebApp.clickByCss(newPiaoJia);
		piaoWuWebApp.sleep(2000);
		piaoWuWebApp.switchToFrame("xubox_iframe1");
		for (int i=1;i<=91;i++){
			String loc1 = "#form-user-add > table > tbody > tr:nth-child("+i+") > td:nth-child(3) > input";
			String loc2 = "#form-user-add > table > tbody > tr:nth-child("+i+") > td:nth-child(4) > input";
			String loc3 = "#form-user-add > table > tbody > tr:nth-child("+i+") > td:nth-child(5) > input";
			String loc4 = "#form-user-add > table > tbody > tr:nth-child("+i+") > td:nth-child(6) > input";
			piaoWuWebApp.clearByCss(loc1);
			piaoWuWebApp.sendKeysByCss(loc1, "2");
			piaoWuWebApp.clearByCss(loc2);
			piaoWuWebApp.sendKeysByCss(loc2, "1");
			piaoWuWebApp.clearByCss(loc3);
			piaoWuWebApp.sendKeysByCss(loc3, "0.01");
			piaoWuWebApp.clearByCss(loc4);
			piaoWuWebApp.sendKeysByCss(loc4, "0.01");
		
		}

		piaoWuWebApp.sleep(1000);
		piaoWuWebApp.clickElement("汽车-票价", "提交");
		
		

		piaoWuWebApp.tool.sleep(2000);

    }


	
}
