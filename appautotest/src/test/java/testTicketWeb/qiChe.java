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
		
		


		
		String luDuan = "#table_main > tbody > tr:nth-child(1) > td:nth-child(13) > a:nth-child(3)";

		String newLuDuan = piaoWuWebApp.getTableRowLocationByCss(luDuan, 6);
		piaoWuWebApp.log(newLuDuan);
		piaoWuWebApp.enterHomePage();
		piaoWuWebApp.clickElement("侧边栏", "长途售票管理");
		piaoWuWebApp.waitDisplay("侧边栏", "汽车线路管理");
		piaoWuWebApp.clickElement("侧边栏", "汽车线路管理");
		piaoWuWebApp.waitDisplayByCss(newLuDuan);
		piaoWuWebApp.clickByCss(newLuDuan);
		piaoWuWebApp.sleep(50000);
		piaoWuWebApp.switchToFrame("xubox_iframe1");
		for (int i=1;i<=91;i++){
			String loc1 = "#form-user-add > table > tbody > tr:nth-child("+i+") > td:nth-child(3) > input";
			String loc2 = "#form-user-add > table > tbody > tr:nth-child("+i+") > td:nth-child(4) > input";
			String loc3 = "#form-user-add > table > tbody > tr:nth-child("+i+") > td:nth-child(5) > input";
			String loc4 = "#form-user-add > table > tbody > tr:nth-child("+i+") > td:nth-child(6) > input";
			WebElement a1 = WebApp.driver.findElement(By.cssSelector(loc1));
			WebElement a2 = WebApp.driver.findElement(By.cssSelector(loc2));
			WebElement a3 = WebApp.driver.findElement(By.cssSelector(loc3));
			WebElement a4 = WebApp.driver.findElement(By.cssSelector(loc4));
			a1.clear();
			a1.sendKeys("2");
			a2.clear();
			a2.sendKeys("1");
			a3.clear();
			a3.sendKeys("0.02");
			a4.clear();
			a4.sendKeys("0.01");
		
		}

		piaoWuWebApp.sleep(1000);
		piaoWuWebApp.clickElement("巴士-票价", "提交");
		
		

		piaoWuWebApp.tool.sleep(2000);

    }


	
}
