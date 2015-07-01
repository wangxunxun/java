package autotest.appautotest;

import java.io.File;   
import java.io.FileInputStream;   
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;   

  

import java.util.HashMap;
import java.util.Map;

import jxl.Cell;   
import jxl.CellType;   
import jxl.Sheet;   
import jxl.Workbook;   
import jxl.read.biff.BiffException;
import jxl.write.Label;   

  

public class gettestdata  

{   
	String excelpath;	
	String currentPath = System.getProperty("user.dir");
	public gettestdata(String excelpath){
		this.excelpath = excelpath;
	}
	
	public Map<String,String>[] testdata() throws BiffException, IOException{
		jxl.Workbook readwb = null;   
		InputStream instream = new FileInputStream(currentPath+"\\"+excelpath);   
		readwb = Workbook.getWorkbook(instream);   
		Sheet readsheet = readwb.getSheet(0);   
		int rsColumns = readsheet.getColumns();   
		int rsRows = readsheet.getRows();   

		String key[] = new String[rsColumns];   
		Map<String,String> data[]= new Map[rsRows];
		for (int j = 0; j < rsColumns; j++){
			Cell cell = readsheet.getCell(j, 0);        	  
			key[j] = cell.getContents();	        	  
		}   	          
	  
		for (int i = 1; i < rsRows; i++)   
	
		{   
			Map<String,String> map=new HashMap<String,String>();
			for (int j = 0; j < rsColumns; j++)   
			{   
				Cell cell = readsheet.getCell(j, i);   
				map.put(key[j], cell.getContents());  
			}   
	      
			data[i-1] = map;	
		}    
		readwb.close();  
		return data;	          	            		
	}

}   