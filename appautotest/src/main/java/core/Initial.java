package core;

import java.util.List;
import java.util.Map;





import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.CommonTools;
import utils.ReadElementData;
import utils.ReadTestData;
public class Initial {
	public static float basicwindowx =720;
	public static float basicwindowy =1280;
	public static WebDriver driver;	
	public WebDriverWait wait; 
	public static CommonTools tool = new CommonTools();
	protected static String main_window;
	
	
	public Map<String, Map<String, Map<String, String>>> initialeledata(String excelpath,String sheetname){
		ReadElementData elementdata = new ReadElementData(excelpath, sheetname);	
		Map<String, Map<String, Map<String, String>>> eledata =elementdata.getdata();
		return eledata;
	}
	
	public List<Map<String, String>> getTestData(String excelpath,String sheetname){
		ReadTestData readtestdata = new ReadTestData();
		List<Map<String, String>> data = readtestdata.getTestData(excelpath, sheetname);
		return data;
	}
	
}
