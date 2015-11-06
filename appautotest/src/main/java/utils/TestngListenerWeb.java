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
import base.WebApp;
@SuppressWarnings("unused")
public class TestngListenerWeb extends TestListenerAdapter {

	CommonTools tool = new CommonTools();

	private String className;
	private String method;
	private Long time;
	private String status;
	private String comment;
	public Map<String, String> methodData = new HashMap<String, String>();
	public static List<Map<String, String>> classData = new ArrayList<Map<String, String>>();

	

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		CommonTools.log(tr.getName() + " Failure");
		try {
			takeScreenShot(tr);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		className = tr.getTestClass().getName();
		method = tr.getName();
		time = tr.getEndMillis() - tr.getStartMillis();
		status = "FAILURE";
        try {
        	comment = tr.getThrowable().getMessage();
        	if(comment.isEmpty()){
        		comment = "message is null";
        	}
        } catch (Exception e) {

        	comment = "message is null";
        	
        }
        
        methodData.put("className", className);
        methodData.put("method", method);
        methodData.put("time", time + "");
        methodData.put("status", status);
        methodData.put("comment", CommonTools.getCurrentTime() + " - Failed " + comment);
        classData.add(methodData);
		
		
	}
	
	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		className = tr.getTestClass().getName();
		method = tr.getName();
		time = tr.getEndMillis() - tr.getStartMillis();
		status = "FAILURE";
        try {
        	comment = tr.getThrowable().getMessage();
        	if(comment.isEmpty()){
        		comment = "message is null";
        	}
        } catch (Exception e) {

        	comment = "message is null";
        	
        }
        
        methodData.put("className", className);
        methodData.put("method", method);
        methodData.put("time", time + "");
        methodData.put("status", status);
        methodData.put("comment", CommonTools.getCurrentTime() + " - Failed " + comment);
        classData.add(methodData);				
	}
	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		className = tr.getTestClass().getName();
		method = tr.getName();
		time = tr.getEndMillis() - tr.getStartMillis();
		status = "FAILURE";
        try {
        	comment = tr.getThrowable().getMessage();
        	if(comment.isEmpty()){
        		comment = "message is null";
        	}
        } catch (Exception e) {

        	comment = "message is null";
        	
        }
        
        methodData.put("className", className);
        methodData.put("method", method);
        methodData.put("time", time + "");
        methodData.put("status", status);
        methodData.put("comment", CommonTools.getCurrentTime() + " - Failed " + comment);
        classData.add(methodData);				
	}
		
	private void takeScreenShot(ITestResult tr) throws InterruptedException, IOException {
		Thread.sleep(3000);
		File scrFile = ((TakesScreenshot) WebApp.driver).getScreenshotAs(OutputType.FILE);		
		String dir_name = CommonTools.setPath("/failTestCaseScreenShot/");
	  	if (!(new File(dir_name).isDirectory())) {  // 判断是否存在该目录
	  		new File(dir_name).mkdir();  // 如果不存在则新建一个目录
	  	}
	  	String filepath = dir_name+CommonTools.getCurrentTime()+ "_"+ tr.getName()+  ".png";
		FileUtils.copyFile(scrFile, new File(filepath));
		Reporter.setCurrentTestResult(tr);
		Reporter.log(filepath);
		Reporter.log("<img src=\"../" + filepath + "\"/>");


	}

}
