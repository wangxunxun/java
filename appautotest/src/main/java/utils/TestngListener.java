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
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import utils.CommonTools;
import core.Initial;


public class TestngListener extends TestListenerAdapter {
	private String className;
	private String method;
	private Long time;
	private String status;
	private String comment;
	public static List<Map<String, String>> classData = new ArrayList<Map<String, String>>();

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		Map<String, String> methodData = new HashMap<String, String>();
		CommonTools.log(tr.getName() + " Failure");
		String screenPath = null;
		try {
			screenPath = takeScreenShot(tr);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		className = tr.getTestClass().getName();
		String[] ddd = className.split("\\.");
		className = ddd[ddd.length - 2] + "." + ddd[ddd.length - 1];
		method = tr.getName();
		time = tr.getEndMillis() - tr.getStartMillis();
		status = "Failure";
		String caseInfo = Initial.caseInfo;
		Initial.caseInfo = null;
		String classInfo = Initial.classInfo;
		Initial.classInfo = null;
		try {
			comment = tr.getThrowable().getMessage();
			if (comment == null) {
				comment = "java.lang.NullPointerException";
			}
		} catch (Exception e) {
			comment = e.toString();

		}

		methodData.put("className", className);
		methodData.put("method", method);
		methodData.put("time", time + "");
		methodData.put("status", status);
		methodData.put("comment", "Failure info - " + comment);
		methodData.put("screenPath", screenPath);
		methodData.put("caseInfo",caseInfo);
		methodData.put("classInfo", classInfo);
		classData.add(methodData);

	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		Map<String, String> methodData = new HashMap<String, String>();
		String screenPath = null;
		className = tr.getTestClass().getName();
		String[] ddd = className.split("\\.");
		className = ddd[ddd.length - 2] + "." + ddd[ddd.length - 1];
		method = tr.getName();
		time = tr.getEndMillis() - tr.getStartMillis();
		status = "Success";
		String successMessage = Initial.successMessage;
		String caseInfo = Initial.caseInfo;
		Initial.caseInfo = null;
		String classInfo = Initial.classInfo;
		Initial.classInfo = null;
		try {
			comment = tr.getThrowable().getMessage();
			if (comment.isEmpty()) {
				if (successMessage == null) {
					comment = "No comment";
				} else {
					comment = successMessage;
					Initial.successMessage = null;
				}
			}
		} catch (Exception e) {

			if (successMessage == null) {
				comment = "No comment";
			} else {
				comment = successMessage;
				Initial.successMessage = null;
			}

		}

		methodData.put("className", className);
		methodData.put("method", method);
		methodData.put("time", time + "");
		methodData.put("status", status);
		methodData.put("comment", "Success info - " + comment);
		methodData.put("screenPath", screenPath);
		methodData.put("caseInfo",caseInfo);
		methodData.put("classInfo", classInfo);
		classData.add(methodData);

	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		Map<String, String> methodData = new HashMap<String, String>();
		String screenPath = null;
		className = tr.getTestClass().getName();
		String[] ddd = className.split("\\.");
		className = ddd[ddd.length - 2] + "." + ddd[ddd.length - 1];
		method = tr.getName();
		time = tr.getEndMillis() - tr.getStartMillis();
		status = "Skipped";
		String caseInfo = Initial.caseInfo;
		Initial.caseInfo = null;
		String classInfo = Initial.classInfo;
		Initial.classInfo = null;
		try {
			comment = tr.getThrowable().getMessage();
			if (comment == null) {
				comment = "No comment";
			}
		} catch (Exception e) {
			comment = "No comment";
		}

		methodData.put("className", className);
		methodData.put("method", method);
		methodData.put("time", time + "");
		methodData.put("status", status);
		methodData.put("comment", "Skipped - " + comment);
		methodData.put("screenPath", screenPath);
		methodData.put("caseInfo",caseInfo);
		methodData.put("classInfo", classInfo);
		classData.add(methodData);
	}

	private String takeScreenShot(ITestResult tr) throws InterruptedException, IOException {
		Thread.sleep(3000);
		File scrFile = ((TakesScreenshot) Initial.driver).getScreenshotAs(OutputType.FILE);
		String dir_name = CommonTools.setPath("/failTestCaseScreenShot/");
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
