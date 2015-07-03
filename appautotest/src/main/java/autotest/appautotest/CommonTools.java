package autotest.appautotest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class CommonTools {
    public String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
        return f.format(date);
    }
    
    public String setCurrentPath(String dirname){
    	String currentPath = System.getProperty("user.dir");
    	return currentPath + dirname;
    }

    
    protected void log(String content, Integer type) {

        switch (type) {
        case 1: {
            System.out.println(getCurrentTime() + " INFO - " + content);
            break;
        }
        case 2: {
            System.err.println(getCurrentTime() + " ERROR - " + content);
            break;
        }
        case 3: {
            System.out.println(getCurrentTime() + " WARNING - " + content);
            break;
        }
        case 4: {
            System.err.println(getCurrentTime() + " WARNING - " + content);
            break;
        }
       }

    }
	
    public void log(String content) {

        log(content, 1);
    }
    
    public void waitByTimeOut(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public Map<String,String>[] getTestData(String excelpath) throws BiffException, IOException{
    	String currentPath = System.getProperty("user.dir");
    	String path = currentPath+"\\"+excelpath;
    	jxl.Workbook readwb = null;   
		InputStream instream = new FileInputStream(path);   
		readwb = Workbook.getWorkbook(instream);   
		Sheet readsheet = readwb.getSheet(0);   
		int rsColumns = readsheet.getColumns();   
		int rsRows = readsheet.getRows();   

		String key[] = new String[rsColumns];   
		Map<String,String> data[]= new Map[rsRows-1];
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
