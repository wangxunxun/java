package utils;

import java.io.File;

import java.io.IOException;

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
	protected static Workbook wb;
	protected static WritableWorkbook wbe;
	protected WritableSheet sheet;

	public OperateExcel(String excelPath,String className) throws BiffException, IOException{
		this.excelPath = excelPath;
		this.className = className;
		wb = Workbook.getWorkbook(new File(this.excelPath));
		wbe= Workbook.createWorkbook(new File(this.excelPath), wb);
		sheet = wbe.getSheet(this.className);
	}
	
	public static Workbook getWb(String excelPath) throws BiffException, IOException{
		wb = Workbook.getWorkbook(new File(excelPath));
		return wb;
	}
	
	public static WritableWorkbook getWbe(String excelPath) throws BiffException, IOException{
		wb = Workbook.getWorkbook(new File(excelPath));
		wbe= Workbook.createWorkbook(new File(excelPath), wb);
		return wbe;
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
		wb.close();
	}

	
	public static void createSheet(String excelPath,String className,int index) throws IOException, WriteException, BiffException{
		Workbook wb = Workbook.getWorkbook(new File(excelPath));
		WritableWorkbook wbe= Workbook.createWorkbook(new File(excelPath), wb);
		wbe.createSheet(className, index);
		wbe.write();
		wbe.close();
		wb.close();
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
		createWorkbook("F:/", "ttt.xls", "1", 0);
		
		createSheet("F:/ttt.xls", "2", 99);
		OperateExcel excel = new OperateExcel("F:/ttt.xls", "2");
		excel.writeLastRow(2, "343434");
		excel.close();
		
		createSheet("F:/ttt.xls", "3", 99);
		OperateExcel excel1 = new OperateExcel("F:/ttt.xls", "3");
		excel1.writeLastRow(2, "3434desww34");
		excel1.close();
		System.out.println(1111);
		
	}
	
	
}
