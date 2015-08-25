package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadElementData {
	private String excelpath;
	private String sheet;
	private Sheet readsheet;

	public ReadElementData(String excelpath,String sheet) {
		this.excelpath = excelpath;
		this.sheet = sheet;
    	jxl.Workbook readwb = null;   
		InputStream instream = null;
		try {
			instream = new FileInputStream(excelpath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		try {
			readwb = Workbook.getWorkbook(instream);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		Sheet readsheet = readwb.getSheet(sheet); 
		this.readsheet = readsheet;
	}
	public List<String> readData(){
		List<String> header =new ArrayList<String>();
		int rsColumns = readsheet.getColumns();   
		int rsRows = readsheet.getRows();   
		if (rsRows==0){
			throw new IllegalStateException("There is now row found in excel file [" + excelpath + "], can't "
					+ "generate map from column name to column index. ");
		}


		for (int j = 0; j < rsColumns; j++){
			Cell cell = readsheet.getCell(j, 0);   
			header.add(cell.getContents());       	  
		}   
		return header;
		
		
	}
	
}
