package autotest.appautotest;
import java.io.IOException;





import jxl.read.biff.BiffException;





import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import initial.piaoWuWebTest;
public class testweb {
	piaoWuWebTest piaoWuWebApp = new piaoWuWebTest();
	String addqichezhan = "添加汽车车站";
	String addBaShiStation = "添加巴士站点";
	String addBaShiXianLu = "巴士添加线路";
	String addBaShiLuDuan = "巴士线路添加路段";
	String addCheLiang = "添加车辆";
	String addSiJi = "添加司机";
	
	String url="http://106.185.47.124:5000/Server/Auth/Login?next=%2F";

	@DataProvider(name="addqichezhan")
	public Object[][] dataProvider1(){		
		return piaoWuWebApp.getTestDataForTestNG(addqichezhan);
	}
	
	@DataProvider(name="addBaShiStation")
	public Object[][] dataProvider2(){		
		return piaoWuWebApp.getTestDataForTestNG(addBaShiStation);
	}
	
	@DataProvider(name="addBaShiXianLu")
	public Object[][] dataProvider3(){		
		return piaoWuWebApp.getTestDataForTestNG(addBaShiXianLu);
	}
	
	@DataProvider(name="addBaShiLuDuan")
	public Object[][] dataProvider4(){		
		return piaoWuWebApp.getTestDataForTestNG(addBaShiLuDuan);
	}
	
	@DataProvider(name="addCheLiang")
	public Object[][] dataProvider5(){		
		return piaoWuWebApp.getTestDataForTestNG(addCheLiang);
	}
	
	@DataProvider(name="addSiJi")
	public Object[][] dataProvider6(){		
		return piaoWuWebApp.getTestDataForTestNG(addSiJi);
	}
	
	@BeforeTest
	public void setUp(){
		piaoWuWebApp.initialTestData();
		piaoWuWebApp.runChormeApp();				
	}


		
	@AfterTest	
	public void tearDown() {
		piaoWuWebApp.quit();
	}

//	@Test(dataProvider="addqichezhan")
    public void addQiCheZhan(String address,String name,String pinyin,String longitude,String latitude,String bashi,String phone,String from,String to,String city) throws InterruptedException, BiffException, IOException{
		
		piaoWuWebApp.get(url);
		piaoWuWebApp.sendKeys("登录页", "登录输入框", "admin");
		piaoWuWebApp.sendKeys("登录页", "密码输入框", "admin");
		piaoWuWebApp.clickElement("登录页", "登录");
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
	
//	@Test(dataProvider="addBaShiStation")
    public void addBaShiStation(String name,String longitude,String latitude) throws InterruptedException, BiffException, IOException{


		piaoWuWebApp.get(url);
		piaoWuWebApp.sendKeys("登录页", "登录输入框", "admin");
		piaoWuWebApp.sendKeys("登录页", "密码输入框", "admin");
		piaoWuWebApp.clickElement("登录页", "登录");
		piaoWuWebApp.clickElement("侧边栏", "巴士售票管理");
		piaoWuWebApp.clickElement("侧边栏", "巴士车站维护");
		piaoWuWebApp.waitDisplay("侧边栏", "巴士车站维护");
		piaoWuWebApp.clickElement("巴士车站维护", "添加车站");
		piaoWuWebApp.switchToFrame("xubox_iframe1");

		piaoWuWebApp.sendKeys("巴士-添加车站", "车站名称",name); 
		piaoWuWebApp.sendKeys("巴士-添加车站", "经度",longitude); 
		piaoWuWebApp.sendKeys("巴士-添加车站", "纬度",latitude); 
		piaoWuWebApp.clickElement("巴士-添加车站", "提交");
		piaoWuWebApp.tool.sleep(1000);

    }
	
//	@Test(dataProvider="addBaShiXianLu")
    public void addBaShiXianLu(String name,String city,String qidian,String zhongdian,String km,String time,String sellNum,String sellDay,String effectiveData) throws InterruptedException, BiffException, IOException{


		piaoWuWebApp.get(url);
		piaoWuWebApp.sendKeys("登录页", "登录输入框", "admin");
		piaoWuWebApp.sendKeys("登录页", "密码输入框", "admin");
		piaoWuWebApp.clickElement("登录页", "登录");
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
		piaoWuWebApp.tool.sleep(1000);

    }
	
	@Test(dataProvider="addBaShiLuDuan")
    public void addBaShiLuDuan(String station,String km,String time) throws InterruptedException, BiffException, IOException{


		piaoWuWebApp.get(url);
		piaoWuWebApp.sendKeys("登录页", "登录输入框", "admin");
		piaoWuWebApp.sendKeys("登录页", "密码输入框", "admin");
		piaoWuWebApp.clickElement("登录页", "登录");
		piaoWuWebApp.clickElement("侧边栏", "巴士售票管理");
		piaoWuWebApp.waitDisplay("侧边栏", "巴士线路维护");
		piaoWuWebApp.clickElement("侧边栏", "巴士线路维护");
		piaoWuWebApp.waitDisplay("巴士线路维护", "路段");
		piaoWuWebApp.clickElement("巴士线路维护", "路段");
		piaoWuWebApp.switchToFrame("xubox_iframe1");

		piaoWuWebApp.sendKeys("巴士-编辑路段", "选择站点",station); 
		piaoWuWebApp.sendKeys("巴士-编辑路段", "距起始站距离",km); 
		piaoWuWebApp.sendKeys("巴士-编辑路段", "距起始站时间",time); 

		piaoWuWebApp.clickElement("巴士-编辑路段", "添加");
		piaoWuWebApp.tool.sleep(2000);

    }
	@Test(dataProvider="addCheLiang")
    public void addCheLiang(String siJi,String cheDui,String carType,String zuCheType,String car_owner,String phone_number,String seat_num,String max_seat_num,String add_seat_num,String plate_number,String buy_year,String engine_number,String brand  ) throws InterruptedException, BiffException, IOException{


		piaoWuWebApp.get(url);
		piaoWuWebApp.sendKeys("登录页", "登录输入框", "admin");
		piaoWuWebApp.sendKeys("登录页", "密码输入框", "admin");
		piaoWuWebApp.clickElement("登录页", "登录");
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
		piaoWuWebApp.tool.sleep(2000);

    }
	
	@Test(dataProvider="addSiJi")
    public void addSiJi(String number,String name,String cheDui,String phone,String xingBie,String idCard,String workNo,String licenseNo,String driverYear,String driverType) throws InterruptedException, BiffException, IOException{


		piaoWuWebApp.get(url);
		piaoWuWebApp.sendKeys("登录页", "登录输入框", "admin");
		piaoWuWebApp.sendKeys("登录页", "密码输入框", "admin");
		piaoWuWebApp.clickElement("登录页", "登录");
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
		piaoWuWebApp.tool.sleep(2000);

    }
}
