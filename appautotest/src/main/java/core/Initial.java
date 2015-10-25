package core;

import java.io.File;
import java.io.FileWriter;
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
	
	
	protected String configFileName;
	//屏幕分辨率设置
	protected int basicWindowX;
	protected int basicWindowY;	
	//andorid配置信息
	protected File classpathRoot = new File(System.getProperty("user.dir"));
	protected File appDir = new File(classpathRoot, "/testresource/apps");
	protected boolean unicodeKeyboard = true;
	protected boolean resetKeyboard = true;
	protected String androidDeviceName;
	protected int waitTime;
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
	protected String logPath;
	
	//测试数据变量
	protected String testExcelPath;
	protected String elementSheet;
	protected String testCaseSheet;
	protected Map<String, Map<String, Map<String, String>>> elementData;
	protected Map<String, Object> testCaseData;
	
	protected String appClass;
	protected String testCaseClassName;
	protected boolean writeScript;
	protected boolean writeResult;
	protected boolean logSwitch;
	protected boolean deleteLogFileFirst;
    
	
	public void setTestClassName(String value){
		testCaseClassName = value;
	}
	
	public String getTestClassName(){
		if (testCaseClassName ==null){
			return "default";
		}
		return testCaseClassName;
	}
	public String getApkName(){
		return apkName;
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
	
	public String getScreenPath(){
		screenPath = getProperties("screenPath");
		if(screenPath!=null){
			return CommonTools.setPath(screenPath);
		}

		return CommonTools.setPath("/screenshot/");
		
	}
	
	protected String getlogPath(){
		logPath = getProperties("logPath");
		if(logPath!=null){
			return CommonTools.setPath(logPath);
		}
		return CommonTools.setPath("/logDefault/");
		
	}
	
	protected void writeTable(Integer row,Integer cow,String content){

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

		if(writeResult==true){
			writeTable(row, cow, result);
		}

	}
	protected void logResult(Integer row){

		if(logSwitch==true){
			String content = "The "+row+"th step is pass.";
			CommonTools.log(content);
		}
	}
	
	public void log(String content){
		if(logSwitch==true){
			CommonTools.log(content);
			writeLog(content);
		}
	}
	
	protected void writeScript(Integer row,Integer cow,String script){

		if(writeScript==true){
			writeTable(row, cow, script);
		}
	}
	
	public void sleep(int time){
		log("Sleep "+time+" ms.");
		CommonTools.sleep(time);
	}

	private void deleteFile(String filePath){
		File f = new File(filePath);
		if(f.exists()){
			f.delete();
		}
	}
	private void deleteFirstTime(String filePath){
		if(deleteLogFileFirst ==true){
			deleteFile(filePath);
			deleteLogFileFirst = false;
		}
	}
	
	private String getLogFilePath(){
		return logPath+getTestClassName()+".txt";
	}
	
	protected void writeLog(String content){
		deleteFirstTime(getLogFilePath());
		writeLog(getTestClassName()+".txt", CommonTools.getCurrentTime()+" INFO - "+content);
	}
	
	private void writeLog(String fileName,String content){
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
	
	}
	
	protected void initialAndroidData(){
		initialData();
		apkName = getProperties("apkName");
		appPackage = getProperties("appPackage");
		mainActivity = getProperties("mainActivity");
		basicWindowX = getBasicWindowX();
		basicWindowY = getBasicWindowY();
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
