package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.read.biff.BiffException;
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
	// app存放文件夹
	protected String appDir;
	// andorid配置信息
	protected int basicWindowX;
	protected int basicWindowY;
	protected boolean unicodeKeyboard;
	protected boolean resetKeyboard;
	protected String androidDeviceName;
	protected String apkName;
	protected String appPackage;
	protected String mainActivity;

	// IOS配置信息
	protected String app;
	protected String platformVersion;
	protected String platform;
	protected String platformName;
	protected String browserName;
	protected String iosDeviceName;

	// 截屏存放路径
	protected String screenDir;
	// log存放路径
	protected String logDir;

	//测试报告存储文件夹
	protected String testReportDir;
	//测试报告名称
	protected String testReportName;
	//当前运行测试class的类名
	protected String testClassName;
	//测试数据存储路径
	protected String testDataExcelPath;
	//元素定位对应sheet名
	protected String elementSheet;
	//测试用例对应sheet名
	protected String testCaseSheet;
	//元素定位对象
	protected Map<String, Map<String, Map<String, String>>> elementData;
	//测试用例对象
	protected Map<String, Object> testCaseData;
	// 测试app的对象名
	protected String appClass;

	// 回写用例脚本开关
	protected boolean writeScript;
	// 回写测试结果开关
	protected boolean writeResult;
	// log开关
	protected boolean logSwitch;
	// 每次运行时是否删除log.text文件开关
	protected boolean deleteLogFileFirst;
	//测试报告对应excel文档的对象
	protected OperateExcel testReportExcel;
	//测试用例对应excel文档的对象
	protected OperateExcel testCaseExcel;
	//每个case执行成功后的message
	public static String successMessage;
	//测试报告testsummary sheet名称
	protected String testSummarySheetName;
	//测试报告名称中转变量
	public static List<String> reportName = new ArrayList<String>();

	protected String getTestReportDir() {
		testReportDir = getProperties("testReportDir");
		if (testReportDir != null) {
			return CommonTools.setPath(testReportDir);
		}

		return CommonTools.setPath("/testResource/");
	}
	
	protected String getTestSummarySheetName(){
		testSummarySheetName = getProperties("testSummarySheetName");
		if (testSummarySheetName != null) {
			return testSummarySheetName;
		}

		return "TestSummary";
		
	}

	protected String getTestReportName() {
		testReportName = getProperties("testReportName");
		if (testReportName != null) {
			testReportName = testReportName+"("+CommonTools.getCurrentTime()+")";
			reportName.add(testReportName);
			return reportName.get(0);
		}
		String name = "TestReport"+"("+CommonTools.getCurrentTime()+")";
		reportName.add(name);
		return reportName.get(0);
	}

	protected Map<String, Map<String, Map<String, String>>> getElementData() {
		ReadElementData elementdata = new ReadElementData(testDataExcelPath, elementSheet);
		Map<String, Map<String, Map<String, String>>> eledata = elementdata.getdata();
		return eledata;
	}

	public List<Map<String, String>> getTestData(String testDataSheet) {

		ReadTestData readtestdata = new ReadTestData();
		List<Map<String, String>> data = readtestdata.getTestData(testDataExcelPath, testDataSheet);
		return data;
	}

	public Object[][] getTestDataForTestNG(String testDataSheet) {

		ReadTestData readtestdata = new ReadTestData();
		Object[][] data = readtestdata.getTestDataForTestNG(testDataExcelPath, testDataSheet);
		return data;
	}

	protected Map<String, Object> getTestCaseData() {

		ReadTestCasesData testCaseData = new ReadTestCasesData(testDataExcelPath, testCaseSheet);
		Map<String, Object> data = testCaseData.getdata();
		return data;

	}

	private String getAppClass() {
		appClass = getProperties("appClass");
		if (appClass == null) {
			return "defaultApp";
		}
		return appClass;

	}

	protected int getBasicWindowX() {
		String x = getProperties("basicWindowX");
		if (x != null) {
			basicWindowX = Integer.parseInt(x);
			return basicWindowX;
		}
		return 720;
	}

	protected int getBasicWindowY() {
		String y = getProperties("basicWindowY");
		if (y != null) {
			basicWindowY = Integer.parseInt(y);
			return basicWindowY;
		}
		return 1280;
	}

	protected int getWaitTime() {
		String time = getProperties("waitTime");
		if (time != null) {
			waitTime = Integer.parseInt(time);
			return waitTime;
		}
		return 10;
	}

	public String getScreenDir() {
		screenDir = getProperties("screenDir");
		if (screenDir != null) {
			return CommonTools.setPath(screenDir);
		}

		return CommonTools.setPath("/screenShot/");

	}

	protected String getAppDir() {
		appDir = getProperties("appDir");
		if (appDir != null) {
			return CommonTools.setPath(appDir);
		}
		return CommonTools.setPath("/testResource/apps/");

	}

	protected String getlogDir() {
		logDir = getProperties("logDir");
		if (logDir != null) {
			return CommonTools.setPath(logDir);
		}
		return CommonTools.setPath("/logDefaultFolder/");

	}

	protected void writeTable(int cow, int row, String content) {
		try {
			testCaseExcel.writeData(cow, row, content);
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

	public String getProperties(String name) {
		if (configFileName != null) {
			return CommonTools.getProperties(configFileName, name);
		}
		return null;
	}

	protected void writeResult(int cow, int row, String result) {

		if (writeResult == true) {
			writeTable(cow, row, result);
		}

	}

	protected void logResult(Integer row) {

		if (logSwitch == true) {
			String content = "The " + row + "th step is pass.";
			CommonTools.log(content);
		}
	}

	public void log(Object content) {
		if (logSwitch == true) {
			String time = CommonTools.getCurrentTime();
			System.out.println(time + " INFO - " + content);
			writeLog(time + " INFO - " + content);
			writeLogToExcel(1, time + " INFO - " + content);
		}
	}

	protected void writeScript(int cow, int row, String script) {

		if (writeScript == true) {
			writeTable(cow, row, script);
		}
	}

	private void deleteFirstTime(String filePath) {
		if (deleteLogFileFirst == true) {
			CommonTools.deleteFile(filePath);
			deleteLogFileFirst = false;
		}
	}

	private String getLogFilePath() {
		return logDir + getClassName() + ".txt";
	}

	protected void writeLog(Object content) {
		deleteFirstTime(getLogFilePath());
		writeLog(getClassName() + ".txt", content);
	}

	public void writeLogToExcel(int cow, Object content) {

		try {
			testReportExcel.writeLastRow(cow, content);
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
		try {
			ITestResult it = Reporter.getCurrentTestResult();
			String classNamePath = it.getTestClass().getName();

			return classNamePath;
		} catch (Exception e) {

		}
		return "";
	}

	public String getTestMethodName() {
		try {
			ITestResult it = Reporter.getCurrentTestResult();
			String testMethodName = it.getName();

			return testMethodName;
		} catch (Exception e) {

		}
		return "";
	}

	public void logTestDescription(String content) {
		try {
			ITestResult it = Reporter.getCurrentTestResult();
			String classNamePath = it.getName();
			testReportExcel.writeLastRow(0, classNamePath);
			testReportExcel.writeSameRow(1, content);
		} catch (Exception e) {
			log("None testNG executor detected, test may continue, but highly recommended to migrate your test to testNG.");
		}
	}

	public void logSuccessMessage(String content) {
		successMessage = content;
		log(content);
	}

	protected void createWorkBook(String className, int index) {

		File f = new File(testReportDir + testReportName + ".xls");
		if (!f.exists()) {
			try {
				CommonTools.createWorkbook(testReportDir, testReportName + ".xls", className, index);
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

	}

	protected void createSheet(int index) throws WriteException, BiffException, IOException {
		deleteSheet(testClassName);

		CommonTools.createSheet(testReportDir + testReportName + ".xls", testClassName, index);

	}

	protected void deleteSheet(String sheetName) {
		try {
			CommonTools.deleteSheet(testReportDir + testReportName + ".xls", sheetName);
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

	private void writeLog(String fileName, Object content) {
		FileWriter writer;
		try {
			if (!(new File(logDir).isDirectory())) { // 判断是否存在该目录
				new File(logDir).mkdir(); // 如果不存在则新建一个目录
			}
			writer = new FileWriter(logDir + fileName, true);
			writer.write(content + "\r\n");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected String getTestClassName() {
		String className = getClassName();
		String[] ddd = className.split("\\.");
		return ddd[ddd.length - 2] + "." + ddd[ddd.length - 1];

	}

	protected void initialData() throws WriteException, BiffException, IOException {
		testDataExcelPath = getProperties("testDataExcelPath");
		elementSheet = getProperties("elementSheet");
		testCaseSheet = getProperties("testCaseSheet");

		elementData = getElementData();
		testCaseData = getTestCaseData();
		waitTime = getWaitTime();

		logDir = getlogDir();
		screenDir = getScreenDir();
		appClass = getAppClass();

		writeScript = Boolean.parseBoolean(getProperties("writeScript"));
		writeResult = Boolean.parseBoolean(getProperties("writeResult"));
		logSwitch = Boolean.parseBoolean(getProperties("log"));
		deleteLogFileFirst = Boolean.parseBoolean(getProperties("deleteLogFileFirst"));
		
		appDir = getAppDir();
		testReportDir = getTestReportDir();
		testReportName = getTestReportName();
		testSummarySheetName = getTestSummarySheetName();
		createWorkBook(testSummarySheetName, 0);
		testClassName = getClassName();
		String excelPath = CommonTools.setPath(testDataExcelPath);

		testCaseExcel = new OperateExcel(excelPath, testCaseSheet);

		createSheet(999);

		testReportExcel = new OperateExcel(testReportDir + testReportName + ".xls", testClassName);

		testReportExcel.setColumnView(1, 100);
		testReportExcel.setColumnView(0, 40);

		testReportExcel.setFormat(10, true);
		testReportExcel.setHyperLinkForSheet(0, 0, "back", testSummarySheetName, 0, 0);
		testReportExcel.writeTitle(0, 1, "TestMethod", 14);
		testReportExcel.writeTitle(1, 1, "Log", 14);

	}

	protected void initialAndroidData() throws WriteException, BiffException, IOException {
		initialData();
		apkName = getProperties("apkName");
		appPackage = getProperties("appPackage");
		mainActivity = getProperties("mainActivity");
		basicWindowX = getBasicWindowX();
		basicWindowY = getBasicWindowY();
		unicodeKeyboard = Boolean.parseBoolean(getProperties("unicodeKeyboard"));
		resetKeyboard = Boolean.parseBoolean(getProperties("resetKeyboard"));
	}

	protected void initialIOSData() throws WriteException, BiffException, IOException {
		initialData();
		app = getProperties("app");
		iosDeviceName = getProperties("deviceName");
		platformVersion = getProperties("platformVersion");
		platform = getProperties("platform");
		platformName = getProperties("platformName");
		browserName = getProperties("browserName");
	}

}
