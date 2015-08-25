package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadTestData {
    public List<Map<String, String>> getTestData(String excelpath,String tablename) throws BiffException, IOException{
    	String currentPath = System.getProperty("user.dir");
    	String path = currentPath+"\\"+excelpath;
    	jxl.Workbook readwb = null;   
		InputStream instream = new FileInputStream(path);   
		readwb = Workbook.getWorkbook(instream);   
		Sheet readsheet = readwb.getSheet(tablename);   
		int rsColumns = readsheet.getColumns();   
		int rsRows = readsheet.getRows();   
		if (rsRows==0){
			throw new IllegalStateException("There is now row found in excel file [" + excelpath + "], can't "
					+ "generate map from column name to column index. ");
		}

		String key[] = new String[rsColumns];   
		List<Map<String,String>> data= new ArrayList<Map<String,String>>();
		for (int j = 0; j < rsColumns; j++){
			Cell cell = readsheet.getCell(j, 0);        	  
			key[j] = cell.getContents();	        	  
		}   	          
	  
		for (int i = 1; i < rsRows; i++)   
	
		{   if (readsheet.getCell(0, i).getContents()!=""){
				Map<String,String> map=new HashMap<String,String>();
					for (int j = 0; j < rsColumns; j++)   
					{   
						Cell cell = readsheet.getCell(j, i);   
						map.put(key[j], cell.getContents());  
					}  
					data.add(map);	
			}    
		}
		readwb.close();  
		return data;	          	            			
    }
}
