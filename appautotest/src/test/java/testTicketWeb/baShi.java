package testTicketWeb;

import initial.piaoWuWebTest;

import java.io.IOException;

import jxl.read.biff.BiffException;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class baShi {
	piaoWuWebTest piaoWuWebApp = new piaoWuWebTest();
	
	
	

	
	


	
	@DataProvider(name="addBaShiStation")
	public Object[][] dataProvider2(){		
		String addBaShiStation = piaoWuWebApp.getProperties("addBaShiStation");
		return piaoWuWebApp.getTestDataForTestNG(addBaShiStation);
	}
	
	@DataProvider(name="addBaShiXianLu")
	public Object[][] dataProvider3(){		
		String addBaShiXianLu = piaoWuWebApp.getProperties("addBaShiXianLu");
		return piaoWuWebApp.getTestDataForTestNG(addBaShiXianLu);
	}
	
	@DataProvider(name="addBaShiLuDuan")
	public Object[][] dataProvider4(){		
		String addBaShiLuDuan = piaoWuWebApp.getProperties("addBaShiLuDuan");
		return piaoWuWebApp.getTestDataForTestNG(addBaShiLuDuan);
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


	
	@Test(dataProvider="addBaShiStation")
    public void test001addBaShiStation(String name,String longitude,String latitude) throws InterruptedException, BiffException, IOException{

		piaoWuWebApp.enterHomePage();
		piaoWuWebApp.clickElement("侧边栏", "巴士售票管理");
		piaoWuWebApp.clickElement("侧边栏", "巴士车站维护");
		piaoWuWebApp.waitDisplay("侧边栏", "巴士车站维护");
		piaoWuWebApp.clickElement("巴士车站维护", "添加车站");
		piaoWuWebApp.switchToFrame("xubox_iframe1");

		piaoWuWebApp.sendKeys("巴士-添加车站", "车站名称",name); 
		piaoWuWebApp.sendKeys("巴士-添加车站", "经度",longitude); 
		piaoWuWebApp.sendKeys("巴士-添加车站", "纬度",latitude); 
		piaoWuWebApp.clickElement("巴士-添加车站", "提交");


    }
	
//	@Test(dataProvider="addBaShiXianLu")
    public void test002addBaShiXianLu(String name,String city,String qidian,String zhongdian,String km,String time,String sellNum,String sellDay,String effectiveData) throws InterruptedException, BiffException, IOException{


		piaoWuWebApp.enterHomePage();
		piaoWuWebApp.clickElement("侧边栏", "巴士售票管理");
		piaoWuWebApp.waitDisplay("侧边栏", "巴士线路维护");
		piaoWuWebApp.clickElement("侧边栏", "巴士线路维护");
		piaoWuWebApp.waitDisplay("巴士线路维护", "添加线路");
		piaoWuWebApp.clickElement("巴士线路维护", "添加线路");
		piaoWuWebApp.switchToFrame("xubox_iframe1");

		piaoWuWebApp.sendKeys("巴士-添加线路", "线路名称",name); 
		piaoWuWebApp.sendKeys("巴士-添加线路", "所属城市",city); 
		piaoWuWebApp.sendKeys("巴士-添加线路", "起点站",qidian); 
		piaoWuWebApp.sendKeys("巴士-添加线路", "终点站",zhongdian); 
		piaoWuWebApp.sendKeys("巴士-添加线路", "里程",km); 
		piaoWuWebApp.sendKeys("巴士-添加线路", "耗时",time); 
		piaoWuWebApp.sendKeys("巴士-添加线路", "默认售票张数",sellNum); 
		piaoWuWebApp.sendKeys("巴士-添加线路", "提前预售天数",sellDay); 
		piaoWuWebApp.sendKeys("巴士-添加线路", "生效日期",effectiveData); 
		piaoWuWebApp.clickElement("巴士-添加线路", "提前预售天数");
		piaoWuWebApp.clickElement("巴士-添加线路", "提交");


    }
	
//	@Test(dataProvider="addBaShiLuDuan")
    public void test003addBaShiLuDuan(String station,String km,String time) throws InterruptedException, BiffException, IOException{
		String luDuan = piaoWuWebApp.getProperties("baShiLuDuan");

		String newLuDuan = piaoWuWebApp.getTableRowLocationByCss(luDuan, 6);

		piaoWuWebApp.enterHomePage();
		piaoWuWebApp.clickElement("侧边栏", "巴士售票管理");
		piaoWuWebApp.waitDisplay("侧边栏", "巴士线路维护");
		piaoWuWebApp.clickElement("侧边栏", "巴士线路维护");
		
		
		piaoWuWebApp.waitDisplayByCss(newLuDuan);
		piaoWuWebApp.clickByCss(newLuDuan);
		
//		piaoWuWebApp.waitDisplay("巴士线路维护", "路段");
//		piaoWuWebApp.clickElement("巴士线路维护", "路段");
		piaoWuWebApp.switchToFrame("xubox_iframe1");

		piaoWuWebApp.sendKeys("巴士-编辑路段", "选择站点",station); 
		piaoWuWebApp.sendKeys("巴士-编辑路段", "距起始站距离",km); 
		piaoWuWebApp.sendKeys("巴士-编辑路段", "距起始站时间",time); 

		piaoWuWebApp.clickElement("巴士-编辑路段", "添加");

    }

//	@Test
    public void test004editBaShiPiaoJia() throws InterruptedException, BiffException, IOException{

		String piaoJia = piaoWuWebApp.getProperties("baShiPiaoJia");

		String newPiaoJia = piaoWuWebApp.getTableRowLocationByCss(piaoJia, 6);
		piaoWuWebApp.enterHomePage();
		piaoWuWebApp.clickElement("侧边栏", "巴士售票管理");
		piaoWuWebApp.waitDisplay("侧边栏", "巴士线路维护");
		piaoWuWebApp.clickElement("侧边栏", "巴士线路维护");

		piaoWuWebApp.waitDisplayByCss(newPiaoJia);
		piaoWuWebApp.clickByCss(newPiaoJia);
//		piaoWuWebApp.waitDisplay("巴士线路维护", "票价");
//		piaoWuWebApp.clickElement("巴士线路维护", "票价");
		piaoWuWebApp.switchToFrame("xubox_iframe1");

		for (int i=1;i<=105;i++){
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


		piaoWuWebApp.clickElement("巴士-票价", "提交");
		


    }
}
