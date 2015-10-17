package testTicketWeb;

import initial.piaoWuWebTest;

import java.io.IOException;

import jxl.read.biff.BiffException;


import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class cheDuiAndCheLiang {
	piaoWuWebTest piaoWuWebApp = new piaoWuWebTest();

	
	
	
	


	
	@DataProvider(name="addCheLiang")
	public Object[][] dataProvider5(){		
		String addCheLiang = piaoWuWebApp.getProperties("addCheLiang");
		return piaoWuWebApp.getTestDataForTestNG(addCheLiang);
	}
	
	@DataProvider(name="addSiJi")
	public Object[][] dataProvider6(){		
		String addSiJi = piaoWuWebApp.getProperties("addSiJi");
		return piaoWuWebApp.getTestDataForTestNG(addSiJi);
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

//	@Test(dataProvider="addCheLiang")
    public void addCheLiang(String siJi,String cheDui,String carType,String zuCheType,String car_owner,String phone_number,String seat_num,String max_seat_num,String add_seat_num,String plate_number,String buy_year,String engine_number,String brand  ) throws InterruptedException, BiffException, IOException{


		piaoWuWebApp.enterHomePage();
		piaoWuWebApp.clickElement("侧边栏", "车队与车辆管理");
		piaoWuWebApp.waitDisplay("侧边栏", "车辆管理");
		piaoWuWebApp.clickElement("侧边栏", "车辆管理");
		piaoWuWebApp.waitDisplay("车辆管理", "添加车辆");
		piaoWuWebApp.clickElement("车辆管理", "添加车辆");
		piaoWuWebApp.switchToFrame("xubox_iframe1");

		piaoWuWebApp.sendKeys("添加车辆", "选择司机",siJi); 
		piaoWuWebApp.sendKeys("添加车辆", "所属车队",cheDui); 
		piaoWuWebApp.sendKeys("添加车辆", "选择车类型",carType); 
		piaoWuWebApp.sendKeys("添加车辆", "租车类型",zuCheType); 
		piaoWuWebApp.sendKeys("添加车辆", "车主姓名",car_owner); 
		piaoWuWebApp.sendKeys("添加车辆", "联系方式",phone_number); 
		piaoWuWebApp.sendKeys("添加车辆", "乘客数量",seat_num); 
		piaoWuWebApp.sendKeys("添加车辆", "最大座位数",max_seat_num); 
		piaoWuWebApp.sendKeys("添加车辆", "加座数",add_seat_num); 
		piaoWuWebApp.sendKeys("添加车辆", "车牌号码",plate_number); 
		piaoWuWebApp.sendKeys("添加车辆", "购买时间",buy_year); 
		piaoWuWebApp.sendKeys("添加车辆", "发动机号",engine_number); 
		piaoWuWebApp.clear("添加车辆", "品牌");
		piaoWuWebApp.sendKeys("添加车辆", "品牌",brand); 
		piaoWuWebApp.clickElement("添加车辆", "品牌");

		piaoWuWebApp.clickElement("添加车辆", "提交");


    }
	
	@Test(dataProvider="addSiJi")
    public void addSiJi(String number,String name,String cheDui,String phone,String xingBie,String idCard,String workNo,String licenseNo,String driverYear,String driverType) throws InterruptedException, BiffException, IOException{


		piaoWuWebApp.enterHomePage();
		piaoWuWebApp.clickElement("侧边栏", "车队与车辆管理");
		piaoWuWebApp.waitDisplay("侧边栏", "司机管理");
		piaoWuWebApp.clickElement("侧边栏", "司机管理");
		piaoWuWebApp.waitDisplay("司机管理", "添加司机");
		piaoWuWebApp.clickElement("司机管理", "添加司机");
		piaoWuWebApp.switchToFrame("xubox_iframe1");

		piaoWuWebApp.sendKeys("添加司机", "编号",number); 
		piaoWuWebApp.sendKeys("添加司机", "姓名",name); 
		piaoWuWebApp.sendKeys("添加司机", "所属车队",cheDui); 
		piaoWuWebApp.sendKeys("添加司机", "手机号",phone); 
		if (xingBie.matches("男")){
			piaoWuWebApp.clickElement("添加司机", "性别-男");
		}
		else if (xingBie.matches("女")){
			piaoWuWebApp.clickElement("添加司机", "性别-女");
		}
		else{
			piaoWuWebApp.clickElement("添加司机", "性别-保密");
		}
		
		piaoWuWebApp.sendKeys("添加司机", "身份证号",idCard); 
		piaoWuWebApp.sendKeys("添加司机", "从业资格证",workNo); 
		piaoWuWebApp.sendKeys("添加司机", "驾驶证号",licenseNo); 
		piaoWuWebApp.sendKeys("添加司机", "驾龄",driverYear); 
		piaoWuWebApp.sendKeys("添加司机", "驾照等级",driverType); 


		piaoWuWebApp.clickElement("添加司机", "提交");


    }

}
