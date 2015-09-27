package core;

import java.io.File;
import java.util.List;
import java.util.Map;























import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.CommonTools;
import utils.ReadElementData;
import utils.ReadTestCasesData;
import utils.ReadTestData;
public class Initial {
	public static WebDriver driver;		
	protected WebDriverWait wait; 
	
	
	
	public CommonTools tool = new CommonTools();

	//屏幕分辨率设置
	protected float basicWindowx =720;
	protected float basicWindowy =1280;	
	//andorid配置信息
	protected File classpathRoot = new File(System.getProperty("user.dir"));
	protected File appDir = new File(classpathRoot, "/testresource/apps");
	protected boolean unicodeKeyboard = true;
	protected boolean resetKeyboard = true;
	protected String deviceName = "meizu";
	protected Integer waitTime = 20;
	protected String apkName = null;
	protected String appPackage = null;
	protected String mainActivity = null;
	
	//截屏存放目录
	protected String dirName = tool.setPath("\\screenshot\\");
	
	//测试数据路径变量
	protected String testExcelPath = null;
	protected String elementSheet = null;
	protected String testCaseSheet = null;
	protected String testDataSheet = null;
	
	public Map<String, Map<String, Map<String, String>>> getElementData(){
		ReadElementData elementdata = new ReadElementData(testExcelPath, elementSheet);	
		Map<String, Map<String, Map<String, String>>> eledata =elementdata.getdata();
		return eledata;
	}
	
	public List<Map<String, String>> getTestData(){
		ReadTestData readtestdata = new ReadTestData();
		List<Map<String, String>> data = readtestdata.getTestData(testExcelPath, testDataSheet);
		return data;
	}
	
	public Map<String, Object> getTestCaseData(){
		ReadTestCasesData testCaseData = new ReadTestCasesData(testExcelPath, testCaseSheet);
//		List<List<Object>> data = testCaseData.readTable();
		Map<String, Object> data = testCaseData.getdata();
		return data;
		
	}
	
	public void initialTestData(){
		
	}
	public void runApp() {
		
	}
}
