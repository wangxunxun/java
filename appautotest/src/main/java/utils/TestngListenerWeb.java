package utils;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import utils.CommonTools;
import base.WebApp;
import core.Initial;
@SuppressWarnings("unused")
public class TestngListenerWeb extends TestListenerAdapter {

	CommonTools tool = new CommonTools();

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
		String[] ddd = className.split("\\.");
		className = ddd[ddd.length-2]+"."+ddd[ddd.length-1];
		method = tr.getName();
		time = tr.getEndMillis() - tr.getStartMillis();
		status = "FAILURE";
		String successMessage = Initial.successMessage;
        try {
        	comment = tr.getThrowable().getMessage();
        	if(comment==null){
        		comment = "";
        	}
        } catch (Exception e) {
        	comment = "";

        	
        }
        
        methodData.put("className", className);
        methodData.put("method", method);
        methodData.put("time", time + "");
        methodData.put("status", status);
        methodData.put("comment", CommonTools.getCurrentTime() + " - FAILURE " + comment);
        classData.add(methodData);
//        writeLog("F:/workspace/java/appautotest/testResource/", content);
		System.out.println("className: "+className);
		System.out.println("method: "+method);
		System.out.println("time: "+time);
		System.out.println("status: "+status);
		System.out.println("comment: "+comment);
		
		
	}
	
	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		Map<String, String> methodData = new HashMap<String, String>();
		className = tr.getTestClass().getName();
		String[] ddd = className.split("\\.");
		className = ddd[ddd.length-2]+"."+ddd[ddd.length-1];
		method = tr.getName();
		time = tr.getEndMillis() - tr.getStartMillis();
		status = "Success";
		String successMessage = Initial.successMessage;
        try {
        	comment = tr.getThrowable().getMessage();
        	if(comment.isEmpty()){
        		if(successMessage==null){
        			comment = "";
        		}
        		else{
        			comment = successMessage;
        		}
        	}
        } catch (Exception e) {

    		if(successMessage==null){
    			comment = "";
    		}
    		else{
    			comment = successMessage;
    		}
        	
        }
        
        methodData.put("className", className);
        methodData.put("method", method);
        methodData.put("time", time + "");
        methodData.put("status", status);
        methodData.put("comment", CommonTools.getCurrentTime() + " - Success " + comment);
        classData.add(methodData);				
//        createXml("F:/workspace/java/appautotest/testResource" + className+ ".xml", methodData, className);
		System.out.println("className: "+className);
		System.out.println("method: "+method);
		System.out.println("time: "+time);
		System.out.println("status: "+status);
		System.out.println("comment: "+comment);
	}
	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		Map<String, String> methodData = new HashMap<String, String>();
		className = tr.getTestClass().getName();
		String[] ddd = className.split("\\.");
		className = ddd[ddd.length-2]+"."+ddd[ddd.length-1];
		method = tr.getName();
		time = tr.getEndMillis() - tr.getStartMillis();
		status = "Skipped";
        try {
        	comment = tr.getThrowable().getMessage();
        	if(comment==null){
        		comment = "";
        	}
        } catch (Exception e) {
        	comment = "";    	
        }
        
        methodData.put("className", className);
        methodData.put("method", method);
        methodData.put("time", time + "");
        methodData.put("status", status);
        methodData.put("comment", CommonTools.getCurrentTime() + " - Skipped " + comment);
        classData.add(methodData);				
 //       createXml("F:/workspace/java/appautotest/testResource" + className+ ".xml", methodData, className);
		System.out.println("className: "+className);
		System.out.println("method: "+method);
		System.out.println("time: "+time);
		System.out.println("status: "+status);
		System.out.println("comment: "+comment);
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
