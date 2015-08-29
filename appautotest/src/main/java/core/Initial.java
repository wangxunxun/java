package core;

import java.util.List;
import java.util.Map;



import utils.ReadElementData;
import utils.ReadTestData;

public class Initial {

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
