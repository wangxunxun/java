package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;  

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

public class WriteTestResult {
	private String excelpath;
	private String sheet;

	public WriteTestResult(String excelpath,String sheet){
    	String currentPath = System.getProperty("user.dir");
    	this.excelpath = currentPath+excelpath;
		this.sheet = sheet;

 

 


		}
	public void write(Integer row,Integer cow,String result) throws IOException, RowsExceededException, WriteException, BiffException{
		jxl.Workbook wb =null;
		InputStream is = new FileInputStream(this.excelpath);
		wb = Workbook.getWorkbook(is);
		jxl.write.WritableWorkbook wbe= Workbook.createWorkbook(new File(this.excelpath), wb);
		WritableSheet sheet  = wbe.getSheet(this.sheet);
//		WritableCell cell =sheet.getWritableCell(6, 1);
//		jxl.format.CellFormat cf = cell.getCellFormat();
		jxl.write.Label lbl = new jxl.write.Label(cow, row, result);
//		lbl.setCellFormat(cf);
		sheet.addCell(lbl);
		wbe.write();
		wbe.close();

	}
	
}

