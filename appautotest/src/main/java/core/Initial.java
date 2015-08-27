package core;

import java.util.Map;


import utils.ReadElementData;

public class Initial {

	public Map<String, Map<String, Map<String, String>>> initialeledata(String excelpath,String sheetname){
		ReadElementData elementdata = new ReadElementData(excelpath, sheetname);	
		Map<String, Map<String, Map<String, String>>> eledata =elementdata.getdata();
		return eledata;
	}
	

}
