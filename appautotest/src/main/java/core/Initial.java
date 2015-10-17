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
	
	
	
	public CommonTools tool = new CommonTools();
	
	protected String configFileName;

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
	protected String dirName = tool.setPath("/screenshot/");
	
	//测试数据变量
	protected String testExcelPath = null;
	protected String elementSheet = null;
	protected String testCaseSheet = null;


    public void sleep(int millis){
    	tool.sleep(millis);
    }
    
    public void log(Object content){
    	tool.log(content);
    }
    
	public Map<String, Map<String, Map<String, String>>> getElementData(String testExcelPath,String elementSheet){
		ReadElementData elementdata = new ReadElementData(testExcelPath, elementSheet);	
		Map<String, Map<String, Map<String, String>>> eledata =elementdata.getdata();
		return eledata;
	}
	
	public List<Map<String, String>> getTestData(String testExcelPath,String testDataSheet){
		ReadTestData readtestdata = new ReadTestData();
		List<Map<String, String>> data = readtestdata.getTestData(testExcelPath, testDataSheet);
		return data;
	}
	
	public Object[][] getTestDataForTestNG(String testExcelPath,String testDataSheet){
		ReadTestData readtestdata = new ReadTestData();
		Object[][] data = readtestdata.getTestDataForTestNG(testExcelPath, testDataSheet);
		return data;
	}
	
	public Object[][] getTestDataForTestNG(String testDataSheet){
		ReadTestData readtestdata = new ReadTestData();
		Object[][] data = readtestdata.getTestDataForTestNG(testExcelPath, testDataSheet);
		return data;
	}
	
	public Map<String, Object> getTestCaseData(String testExcelPath,String testCaseSheet){
		ReadTestCasesData testCaseData = new ReadTestCasesData(testExcelPath, testCaseSheet);
		Map<String, Object> data = testCaseData.getdata();
		return data;
		
	}
	
	public void writeResult(Integer row,Integer cow,String result){
		WriteTestResult writeTestResult = new WriteTestResult(testExcelPath, testCaseSheet);
		try {
			try {
				writeTestResult.write(row, cow, result);
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
		return tool.getProperties(configFileName, name);
	}

	


}
