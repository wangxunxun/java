package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import utils.CommonTools;
import core.Initial;
import core.UI;

public class TestngListener extends TestListenerAdapter {
	private String className;
	private String method;
	private Long time;
	private String comment;
	private String screenPath;
	private String successMessage;
	private String caseInfo;
	private String classInfo;
	public static List<Map<String, String>> classData = new ArrayList<Map<String, String>>();

	private void test(ITestResult tr,String status){
		Map<String, String> methodData = new HashMap<String, String>();
		className = tr.getTestClass().getName();
		String[] ddd = className.split("\\.");
		className = ddd[ddd.length - 2] + "." + ddd[ddd.length - 1];
		className = CommonTools.getValidSheetName(className);
		method = tr.getName();
		time = tr.getEndMillis() - tr.getStartMillis();
		successMessage = Initial.successMessage;
		caseInfo = Initial.caseInfo;
		Initial.caseInfo = null;
		classInfo = Initial.classInfo;
		Initial.classInfo = null;
		if(status == "Failure"){
			CommonTools.log(method + " Failure");
				try {
					screenPath = takeScreenShot(tr);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			comment = "Failure info - " + tr.getThrowable().getMessage();

		}
		else if (status =="Success"){
			screenPath = null;
			if (successMessage == null) {
				comment = "Success info - " + "No comment";
			} else {
				comment = "Success info - " + successMessage;
				Initial.successMessage = null;
			}			
		}
		else if (status == "Skipped"){
			screenPath = null;
			comment = "Skipped info - " + tr.getThrowable().getMessage();

		}		
		methodData.put("className", className);
		methodData.put("classInfo", classInfo);
		methodData.put("caseInfo", caseInfo);
		methodData.put("method", method);
		methodData.put("time", time + "");
		methodData.put("status", status);
		methodData.put("comment", comment);
		methodData.put("screenPath", screenPath);		
		classData.add(methodData);
	}
	
	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		test(tr, "Failure");
	}
	
	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		test(tr, "Success");
	}
	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		test(tr, "Skipped");
	}
	

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		System.out.println(tr.getName() + " Start");
	}

	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);
		System.out.println("The cases end on " + testContext.getEndDate());
		System.out.println("The failed case counts is " + testContext.getFailedTests().size());
		System.out.println("The skipped case counts is " + testContext.getSkippedTests().size());
		System.out.println("The success case counts is " + testContext.getPassedTests().size());

	}

	@Override
	public void onStart(ITestContext testContext) {
		super.onStart(testContext);
		System.out.println("The cases start on " + testContext.getStartDate());
		System.out.println("Start to excute " + testContext.getAllTestMethods().length+" case");
	}

	private String takeScreenShot(ITestResult tr) throws InterruptedException, IOException {
		Thread.sleep(3000);
		File scrFile = null;
		String dir_name = null;
        if (Initial.testAppType =="web"){
    		dir_name = Initial.testReportDir+"failScreen/web/";
        } 
        else if(Initial.testAppType =="android"){
        	dir_name = Initial.testReportDir+"failScreen/android/";      	
        }
        else if (Initial.testAppType =="ios"){
        	dir_name = Initial.testReportDir+"failScreen/ios/";
        }
        scrFile = ((TakesScreenshot)UI.driver).getScreenshotAs(OutputType.FILE);

		if (!(new File(dir_name).isDirectory())) { // 判断是否存在该目录
			new File(dir_name).mkdir(); // 如果不存在则新建一个目录
		}
		String filepath = dir_name + CommonTools.getCurrentTime() + "_" + tr.getName() + ".png";
		FileUtils.copyFile(scrFile, new File(filepath));
		Reporter.setCurrentTestResult(tr);
		Reporter.log(filepath);
		Reporter.log("<img src=\"../" + filepath + "\"/>");
		return filepath;

	}

}
