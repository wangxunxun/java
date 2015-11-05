package utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sourceforge.htmlunit.corejs.javascript.ast.LabeledStatement;

public class OperateExcel {
	
	private String excelPath;
	private String className;
	protected Workbook wb;
	protected WritableWorkbook wbe;
	protected WritableSheet sheet;
	protected WritableFont font;
	protected static WritableCellFormat format;

	public OperateExcel(String excelPath,String className) throws BiffException, IOException{
		this.excelPath = excelPath;
		this.className = className;
		wb = Workbook.getWorkbook(new File(this.excelPath));
		wbe= Workbook.createWorkbook(new File(this.excelPath), wb);
		sheet = wbe.getSheet(this.className);
		
	}
	


	public void writeLastRow(int cow,Object content) throws RowsExceededException, WriteException, BiffException, IOException{

		int row = sheet.getRows();
		if (format!=null){
			Label lable = new Label(cow, row, (String) content,format);
			sheet.addCell(lable);
		}
		else{
			Label lable = new Label(cow, row, (String) content);
			sheet.addCell(lable);
		}
	}
	
	public void writeSameRow(int cow,Object content) throws RowsExceededException, WriteException, BiffException, IOException{

		int row = sheet.getRows();
		if (format!=null){
			Label lable = new Label(cow, row-1, (String) content,format);
			sheet.addCell(lable);
		}
		else{
			Label lable = new Label(cow, row-1, (String) content,format);
			sheet.addCell(lable);
		}
	}
	
	public void writeData(int cow,int row,Object content) throws RowsExceededException, WriteException, BiffException, IOException{

		if (format!=null){
			Label lable = new Label(cow, row, (String) content,format);
			sheet.addCell(lable);
		}
		else{
			Label lable = new Label(cow, row, (String) content);
			sheet.addCell(lable);
		}
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
		Label lbl = new Label(cow, row, (String) content,format);
		sheet.addCell(lbl);
		wbe.write();
		wbe.close();
	}
	public static void writeLastRow(String excelPath,String className,int cow,Object content) throws BiffException, IOException, RowsExceededException, WriteException{
		Workbook wb = Workbook.getWorkbook(new File(excelPath));
		WritableWorkbook wbe= Workbook.createWorkbook(new File(excelPath), wb);
		WritableSheet sheet = wbe.getSheet(className);
		int row = sheet.getRows();
		Label lbl = new Label(cow, row, (String) content,format);
		sheet.addCell(lbl);
		wbe.write();
		wbe.close();
		
	}
	public void mergeCells(int col1,int row1,int col2,int row2) throws RowsExceededException, WriteException{
		sheet.mergeCells(col1, row1, col2, row2);
	}
	public void setColumnView(int col,int width){
		sheet.setColumnView(col, width);
	}
	
	public void writeTitle(int cow,int row,String content,int size) throws RowsExceededException, WriteException{
		WritableFont font = new WritableFont(WritableFont.ARIAL, size ,WritableFont.BOLD);
		WritableCellFormat format = new WritableCellFormat(font);
		Label label = new Label(cow,row,content,format);
		sheet.addCell(label);
	}
	
	public void setFormat(int size,Boolean wrap) throws WriteException{
		font = new WritableFont(WritableFont.ARIAL, size);
		format = new WritableCellFormat(font);
		format.setWrap(wrap);
	}
	
	
	public static void deleteSheet(String excelPath,String name) throws BiffException, IOException, WriteException{
		Workbook wb = Workbook.getWorkbook(new File(excelPath));
		WritableWorkbook wbe= Workbook.createWorkbook(new File(excelPath), wb);
		String[] sheetNames = wbe.getSheetNames();
		Map<String,String> map = new HashMap<String,String>();
		for(int i =0;i<sheetNames.length;i++){
			String j = Integer.toString(i);
			map.put(sheetNames[i], j);
		}
		for(String sheetName :sheetNames){
			if(sheetName.contains(name)){
				wbe.removeSheet(Integer.parseInt(map.get(sheetName)));
			}
			
		}
		wbe.write();
		wbe.close();
		wb.close();
	}

	public static void main(String[] args) throws RowsExceededException, BiffException, WriteException, IOException {

		OperateExcel excel = new OperateExcel("F:/testReport.xls", "testTicketWeb.baShi");
//		excel.setColumnView(1, 60);
//		excel.setFormat(19, true);
		excel.writeData(0, 0, "3434343434343434354354545454545454545454545454545");
		excel.close();
		System.out.println("end");
	}
	
	
}
