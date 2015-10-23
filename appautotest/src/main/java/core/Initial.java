package core;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.CommonTools;
import utils.ReadElementData;
import utils.ReadTestCasesData;
import utils.ReadTestData;
import utils.WriteTestResult;
public class Initial {

	public static WebDriver driver;		
	protected WebDriverWait wait; 
	
	
	protected String configFileName = null;

	//屏幕分辨率设置
	protected float basicWindowx =720;
	protected float basicWindowy =1280;	
	//andorid配置信息
	protected File classpathRoot = new File(System.getProperty("user.dir"));
	protected File appDir = new File(classpathRoot, "/testresource/apps");
	protected boolean unicodeKeyboard = true;
	protected boolean resetKeyboard = true;
	protected String deviceName = "meizu";
	protected Integer waitTime = 10;
	protected String apkName = null;
	protected String appPackage = null;
	protected String mainActivity = null;
	
	//IOS配置信息
	protected String app;
	protected String platformVersion;
	protected String platform;
	protected String platformName;
	protected String browserName;



	//截屏存放目录
//	protected String dirName = CommonTools.setPath("/screenshot/");
	protected String screenPath;
	
	//测试数据变量
	protected String testExcelPath = null;
	protected String elementSheet = null;
	protected String testCaseSheet = null;
	protected Map<String, Map<String, Map<String, String>>> elementData;
	protected Map<String, Object> testCaseData;
	protected String appClass;
	protected boolean writeScript;
	protected boolean writeResult;
	protected boolean log;
    
    protected Map<String, Map<String, Map<String, String>>> getElementData(){
		if (configFileName != null){
			testExcelPath = getProperties("testExcelPath");
			elementSheet = getProperties("elementSheet");
		}
		ReadElementData elementdata = new ReadElementData(testExcelPath, elementSheet);	
		Map<String, Map<String, Map<String, String>>> eledata =elementdata.getdata();
		return eledata;
	}
	
	public List<Map<String, String>> getTestData(String testExcelPath,String testDataSheet){
		if (configFileName != null){
			testExcelPath = getProperties("testExcelPath");
		}
		ReadTestData readtestdata = new ReadTestData();
		List<Map<String, String>> data = readtestdata.getTestData(testExcelPath, testDataSheet);
		return data;
	}
	

	
	public Object[][] getTestDataForTestNG(String testDataSheet){
		if (configFileName != null){
			testExcelPath = getProperties("testExcelPath");
		}
		ReadTestData readtestdata = new ReadTestData();
		Object[][] data = readtestdata.getTestDataForTestNG(testExcelPath, testDataSheet);
		return data;
	}
	
	protected Map<String, Object> getTestCaseData(){
		if (configFileName != null){
			testExcelPath = getProperties("testExcelPath");
			testCaseSheet = getProperties("testCaseSheet");
		}
		ReadTestCasesData testCaseData = new ReadTestCasesData(testExcelPath, testCaseSheet);
		Map<String, Object> data = testCaseData.getdata();
		return data;
		
	}
	
	protected String getScreenPath(){
		String path =null;
		if (configFileName != null){
			path = getProperties("screenPath");
			if(path!=null){
				return CommonTools.setPath(path);
			}
						
		}
		return CommonTools.setPath("/screenshot/");
		
	}
	
	protected void writeTable(Integer row,Integer cow,String content){
		if (configFileName != null){
			testExcelPath = getProperties("testExcelPath");
			testCaseSheet = getProperties("testCaseSheet");
		}
		WriteTestResult writeTestResult = new WriteTestResult(testExcelPath, testCaseSheet);
		try {
			try {
				writeTestResult.write(row, cow, content);
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
	}
	
	public String getProperties(String name){
		if(configFileName!=null){
			return CommonTools.getProperties(configFileName, name);
		}
		return null;
	}

	public String getProperties(String configFileName, String name){

		return CommonTools.getProperties(configFileName, name);

	}

	protected void writeResult(Integer row,Integer cow,String result){
		if(configFileName !=null){
			if (getProperties("writeResult").matches("true")){
				writeTable(row, cow, result);
			}

		}
		else{
			if(writeResult==true){
				writeTable(row, cow, result);
			}
		}
	}
	protected void logResult(Integer row){
		if(configFileName !=null){
			if (getProperties("log").matches("true")){
				String content = "The "+row+"th step is pass.";
				CommonTools.log(content);
			}

		}
		else{
			if(log==true){
				String content = "The "+row+"th step is pass.";
				CommonTools.log(content);
			}
		}
	}
	
	protected void log(Object content){
		if(configFileName !=null){
			if (getProperties("log").matches("true")){
				CommonTools.log(content);
			}

		}
		else{
			if(log==true){
				CommonTools.log(content);
			}
		}
	}
	
	protected void writeScript(Integer row,Integer cow,String script){

		if(configFileName !=null){
			if (getProperties("writeScript").matches("true")){
				writeTable(row, cow, script);
			}

		}
		else{
			if(writeScript==true){
				writeTable(row, cow, script);
			}
		}
	}
	
	public void sleep(int time){
		log("Sleep "+time+" ms.");
		CommonTools.sleep(time);
	}


}
