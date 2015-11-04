package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;

import utils.CommonTools;
import utils.OperateExcel;
import utils.ReadElementData;
import utils.ReadTestCasesData;
import utils.ReadTestData;
public class Initial {

	public static WebDriver driver;		
	protected WebDriverWait wait; 
	protected int waitTime;	

	protected String configFileName;
	//app存放文件夹
	protected String appDir;
	//andorid配置信息
	protected int basicWindowX;
	protected int basicWindowY;	
	protected boolean unicodeKeyboard;
	protected boolean resetKeyboard;
	protected String androidDeviceName;
	protected String apkName;
	protected String appPackage;
	protected String mainActivity;
	
	//IOS配置信息
	protected String app;
	protected String platformVersion;
	protected String platform;
	protected String platformName;
	protected String browserName;
	protected String iosDeviceName;



	//截屏存放目录
	protected String screenPath;
	//log存放路径
	protected String logPath;
	
	//测试数据变量
	protected String testReportPath = "/Users/wangxun/Documents/workspace/java/appautotest/testresource/";
	protected String testExcelName = "testReport";
	protected String testExcelPath;
	protected String elementSheet;
	protected String testCaseSheet;
	protected Map<String, Map<String, Map<String, String>>> elementData;
	protected Map<String, Object> testCaseData;
	//测试app的类名
	protected String appClass;
	//测试用例的类名
	protected String testCaseClassName;
	//回写脚本开关
	protected boolean writeScript;
	//回写测试结果开关
	protected boolean writeResult;
	//log开关
	protected boolean logSwitch;
	//每次运行时是否删除log文件开关
	protected boolean deleteLogFileFirst;
	protected OperateExcel excel;

	protected String testClassName;
	protected String testMethodName;
	
	
	public void setTestClassName(String value){
		testCaseClassName = value;
	}
	
	public String getTestClassName(){
		if (testCaseClassName ==null){
			return "default";
		}
		return testCaseClassName;
	}

    protected Map<String, Map<String, Map<String, String>>> getElementData(){
		ReadElementData elementdata = new ReadElementData(testExcelPath, elementSheet);	
		Map<String, Map<String, Map<String, String>>> eledata =elementdata.getdata();
		return eledata;
	}
	
	public List<Map<String, String>> getTestData(String testDataSheet){

		ReadTestData readtestdata = new ReadTestData();
		List<Map<String, String>> data = readtestdata.getTestData(testExcelPath, testDataSheet);
		return data;
	}
	

	
	public Object[][] getTestDataForTestNG(String testDataSheet){

		ReadTestData readtestdata = new ReadTestData();
		Object[][] data = readtestdata.getTestDataForTestNG(testExcelPath, testDataSheet);
		return data;
	}
	
	protected Map<String, Object> getTestCaseData(){


		ReadTestCasesData testCaseData = new ReadTestCasesData(testExcelPath, testCaseSheet);
		Map<String, Object> data = testCaseData.getdata();
		return data;
		
	}
	
	private String getAppClass(){
		appClass = getProperties("appClass");
    	if(appClass ==null){
    		return "app";
    	}
    	return appClass;

	}
	
	protected int getBasicWindowX(){
		String x = getProperties("basicWindowX");		
		if(x!=null){
			basicWindowX = Integer.parseInt(x);
			return basicWindowX;
		}
		return 720;
	}
	
	protected int getBasicWindowY(){
		String y = getProperties("basicWindowY");
		if(y!=null){
			basicWindowY = Integer.parseInt(y);
			return basicWindowY;
		}
		return 1280;
	}
	protected int getWaitTime(){
		String time = getProperties("waitTime");		
		if(time!=null){
			waitTime = Integer.parseInt(time);
			return waitTime;
		}
		return 10;
	}
	public String getScreenPath(){
		screenPath = getProperties("screenPath");
		if(screenPath!=null){
			return CommonTools.setPath(screenPath);
		}

		return CommonTools.setPath("/screenShot/");
		
	}
	
	protected String getAppDir(){
		appDir = getProperties("appDir");
		if(appDir!=null){
			return CommonTools.setPath(appDir);
		}
		return CommonTools.setPath("/testResource/apps/");
		
	}
	
	protected String getlogPath(){
		logPath = getProperties("logPath");
		if(logPath!=null){
			return CommonTools.setPath(logPath);
		}
		return CommonTools.setPath("/logDefault/");
		
	}
	
	protected void writeTable(int cow,int row,String content){
		String excelPath = CommonTools.setPath(testExcelPath);
		try {
			OperateExcel.writeContent(excelPath, testCaseSheet, cow, row, content);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
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


	protected void writeResult(int cow,int row,String result){

		if(writeResult==true){
			writeTable(cow, row, result);
		}

	}
	protected void logResult(Integer row){

		if(logSwitch==true){
			String content = "The "+row+"th step is pass.";
			CommonTools.log(content);
		}
	}
	
	public void log(Object content){
		if(logSwitch==true){
			CommonTools.log(content);
			writeLog(content);
			writetest(1, content);
		}
	}
	
	protected void writeScript(int cow,int row,String script){

		if(writeScript==true){
			writeTable(cow,row,script);
		}
	}
	


	private void deleteFirstTime(String filePath){
		if(deleteLogFileFirst ==true){
			CommonTools.deleteFile(filePath);
			deleteLogFileFirst = false;
		}
	}
	
	private String getLogFilePath(){
		return logPath+testCaseClassName+".txt";
	}
	
	protected void writeLog(Object content){
		deleteFirstTime(getLogFilePath());
		writeLog(testCaseClassName+".txt", CommonTools.getCurrentTime()+" INFO - "+content);
	}
	
	protected void writeLogToExcel(String excelPath,String className,int cow,int row,Object content){
		try {
			OperateExcel.writeContent(excelPath, className, cow, row, content);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
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
	
	public void writeLogToExcelLastRow(String excelPath,String className,int cow,Object content){
		try {
			OperateExcel.writeLastRow(excelPath, className, cow, content);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
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
	
	public void writetest(int cow,Object content){

		try {
			excel.writeLastRow(cow, content);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public String getClassName() {
       // log(">==================================================<");
        try {
            ITestResult it = Reporter.getCurrentTestResult();
            String classNamePath = it.getTestClass().getName();
            
			return classNamePath;
        } catch (Exception e) {

        }
        return "";
    }
    public String logTestDescription(String content) {
        try {
            ITestResult it = Reporter.getCurrentTestResult();

            String classNamePath = it.getName();
            excel.writeLastRow(0, classNamePath);
            excel.writeSameRow(1, content);
			return classNamePath;
        } catch (Exception e) {
            log("None testNG executor detected, test may continue, but highly recommended to migrate your test to testNG.");
        }
        return "";
    }
	
	protected void createWorkBook(String className,int index){

		CommonTools.deleteFile(testReportPath+testExcelName+".xls");
		try {
			OperateExcel.createWorkbook(testReportPath, testExcelName+".xls",className, index);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	protected void createSheet(String className,int index){
		try {
			OperateExcel.createSheet(testReportPath+testExcelName+".xls", className, index);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeLog(String fileName,Object content){
		FileWriter writer;
		try {
		  	if (!(new File(logPath).isDirectory())) {  // 判断是否存在该目录
		  		new File(logPath).mkdir();  // 如果不存在则新建一个目录
		  	}
			writer = new FileWriter(logPath+fileName,true);
			writer.write(content+"\r\n");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	protected void initialData() {
		testExcelPath = getProperties("testExcelPath");
		elementSheet = getProperties("elementSheet");    	
		testCaseSheet = getProperties("testCaseSheet");
    	
    	elementData =getElementData();
		testCaseData = getTestCaseData();
		waitTime = getWaitTime();
		
		logPath = getlogPath();
		screenPath = getScreenPath();
		appClass = getAppClass();
		
		writeScript = Boolean.parseBoolean(getProperties("writeScript"));
		writeResult = Boolean.parseBoolean(getProperties("writeResult"));
		logSwitch = Boolean.parseBoolean(getProperties("log"));
		deleteLogFileFirst = Boolean.parseBoolean(getProperties("deleteLogFileFirst"));
		appDir = getAppDir();
		createWorkBook("TestSummary", 0);
	
	}
	
	protected void initialAndroidData(){
		initialData();
		apkName = getProperties("apkName");
		appPackage = getProperties("appPackage");
		mainActivity = getProperties("mainActivity");
		basicWindowX = getBasicWindowX();
		basicWindowY = getBasicWindowY();
		unicodeKeyboard = Boolean.parseBoolean(getProperties("unicodeKeyboard"));
		resetKeyboard = Boolean.parseBoolean(getProperties("resetKeyboard"));
	}
	
	protected void initialIOSData(){
		initialData();
		app = getProperties("app");
		iosDeviceName = getProperties("deviceName");
		platformVersion = getProperties("platformVersion");
		platform = getProperties("platform");
		platformName = getProperties("platformName");
		browserName = getProperties("browserName");
	}

}
