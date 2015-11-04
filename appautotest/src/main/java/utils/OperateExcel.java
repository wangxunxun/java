package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class OperateExcel {
	
	private String excelPath;
	private String className;
	protected Workbook wb;
	protected WritableWorkbook wbe;
	protected WritableSheet sheet;

	public OperateExcel(String excelPath,String className) throws BiffException, IOException{
		this.excelPath = excelPath;
		this.className = className;
		wb = Workbook.getWorkbook(new File(this.excelPath));
		wbe= Workbook.createWorkbook(new File(this.excelPath), wb);
		sheet = wbe.getSheet(this.className);
	}

	public void writeLastRow(int cow,Object content) throws RowsExceededException, WriteException, BiffException, IOException{

		int row = sheet.getRows();
		Label lbl = new Label(cow, row, (String) content);
		sheet.addCell(lbl);
	}
	
	public void writeSameRow(int cow,Object content) throws RowsExceededException, WriteException, BiffException, IOException{

		int row = sheet.getRows();
		Label lbl = new Label(cow, row-1, (String) content);
		sheet.addCell(lbl);
	}
	
	public void writeData(int cow,int row,Object content) throws RowsExceededException, WriteException, BiffException, IOException{

		Label lbl = new Label(cow, row, (String) content);
		sheet.addCell(lbl);
	}
	
	public void close() throws IOException, WriteException{
		wbe.write();
		wbe.close();
	}

	
	public static void createSheet(String excelPath,String className,int index) throws IOException, WriteException, BiffException{
		Workbook wb = Workbook.getWorkbook(new File(excelPath));
		WritableWorkbook wbe= Workbook.createWorkbook(new File(excelPath), wb);
		wbe.createSheet(className, index);
		wbe.write();
		wbe.close();
	}
	public static void createWorkbook(String excelDir,String excelName,String className,int index) throws IOException, WriteException, BiffException{
		WritableWorkbook wb = Workbook.createWorkbook(new File(excelDir+excelName));
		wb.createSheet(className, index);
		wb.write();
		wb.close();
	}

	public static void writeContent(String excelPath,String className,int cow,int row,Object content) throws BiffException, IOException, RowsExceededException, WriteException{
		Workbook wb =null;
		wb = Workbook.getWorkbook(new File(excelPath));
		WritableWorkbook wbe= Workbook.createWorkbook(new File(excelPath), wb);
		WritableSheet sheet = wbe.getSheet(className);
		Label lbl = new Label(cow, row, (String) content);
		sheet.addCell(lbl);
		wbe.write();
		wbe.close();
	}
	public static void writeLastRow(String excelPath,String className,int cow,Object content) throws BiffException, IOException, RowsExceededException, WriteException{
		Workbook wb = Workbook.getWorkbook(new File(excelPath));
		WritableWorkbook wbe= Workbook.createWorkbook(new File(excelPath), wb);
		WritableSheet sheet = wbe.getSheet(className);
		int row = sheet.getRows();
		Label lbl = new Label(cow, row, (String) content);
		sheet.addCell(lbl);
		wbe.write();
		wbe.close();
		
	}

	public static void main(String[] args) throws RowsExceededException, BiffException, WriteException, IOException {
//		createWorkBook("F:/test11.xls", "haha", 0);
//		createWorkBook("F:/test.xls", "haha", 0);
//		createSheet("F:/test11.xls", "haha33545", 0);
//		createSheet("F:/test11.xls", "haha33", 0);
		writeContent("/Users/wangxun/Documents/workspace/java/appautotest/testresource/test.xls", "test", 3, 3, "565656");
//		writeContent("F:/test.xls", "haha", 4, 4, "56566656");
//		writeLastRow("F:/test.xls", "haha", 6, "56566656");
//		writeLastRow("F:/test.xls", "haha", 6, "56566656");
	}
	
	
}
