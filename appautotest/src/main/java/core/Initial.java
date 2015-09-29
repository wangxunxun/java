package core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
	private static final Integer Integer = null;
	private static final String String = null;
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
	
	//测试数据变量
	protected String testExcelPath = "testresource\\test.xls";;
	protected String elementSheet = "票务系统";
	protected String testCaseSheet = "票务系统测试用例";
	protected String testDataSheet = null;
	protected Map<String,String> testResult = new HashMap<String, String>();
	protected WriteTestResult writeTestResult = new WriteTestResult(testExcelPath, testCaseSheet);
	
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
	
	public Map<String, Object> getTestCaseData(String testExcelPath,String testCaseSheet){
		ReadTestCasesData testCaseData = new ReadTestCasesData(testExcelPath, testCaseSheet);
//		List<List<Object>> data = testCaseData.readTable();
		Map<String, Object> data = testCaseData.getdata();
		return data;
		
	}
	
	public void writeResult(Integer row,Integer cow,String result){
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
	

	

	


}
