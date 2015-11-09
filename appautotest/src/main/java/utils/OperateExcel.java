package utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class OperateExcel {

	protected Workbook wb;
	protected WritableWorkbook wbe;
	protected WritableSheet sheet;
	protected WritableFont font;
	protected static WritableCellFormat format;

	public OperateExcel(String excelPath, String className)
			throws BiffException, IOException {

		wb = Workbook.getWorkbook(new File(excelPath));
		wbe = Workbook.createWorkbook(new File(excelPath), wb);
		sheet = wbe.getSheet(className);

	}

	public void writeLastRow(int cow, Object content)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		int row = sheet.getRows();
		if (format != null) {
			Label lable = new Label(cow, row, (String) content, format);
			sheet.addCell(lable);
		} else {
			Label lable = new Label(cow, row, (String) content);
			sheet.addCell(lable);
		}
	}

	public void writeSameRow(int cow, Object content)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		int row = sheet.getRows();
		if (format != null) {
			Label lable = new Label(cow, row - 1, (String) content, format);
			sheet.addCell(lable);
		} else {
			Label lable = new Label(cow, row - 1, (String) content, format);
			sheet.addCell(lable);
		}
	}

	public void writeData(int cow, int row, Object content)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		if (format != null) {
			Label lable = new Label(cow, row, (String) content, format);
			sheet.addCell(lable);
		} else {
			Label lable = new Label(cow, row, (String) content);
			sheet.addCell(lable);
		}
	}

	public void close() throws IOException, WriteException {
		wbe.write();
		wbe.close();
	}

	public void mergeCells(int col1, int row1, int col2, int row2)
			throws RowsExceededException, WriteException {
		sheet.mergeCells(col1, row1, col2, row2);
	}

	public void setColumnView(int col, int width) {
		sheet.setColumnView(col, width);
	}

	public void writeTitle(int cow, int row, String content, int size)
			throws RowsExceededException, WriteException {
		WritableFont font = new WritableFont(WritableFont.ARIAL, size,
				WritableFont.BOLD);
		WritableCellFormat format = new WritableCellFormat(font);
		Label label = new Label(cow, row, content, format);
		sheet.addCell(label);
	}

	public void writeDataByColour(int cow, int row, String content, int size,
			Colour colour) throws RowsExceededException, WriteException {
		WritableFont font = new WritableFont(WritableFont.ARIAL, size);
		WritableCellFormat format = new WritableCellFormat(font);
		format.setBackground(colour);
		Label label = new Label(cow, row, content, format);
		sheet.addCell(label);
	}

	public void setFormat(int size, Boolean wrap) throws WriteException {
		font = new WritableFont(WritableFont.ARIAL, size);
		format = new WritableCellFormat(font);
		format.setWrap(wrap);
	}

	public void setHyperLinkForFile(int cow, int row, String filePath)
			throws MalformedURLException, RowsExceededException, WriteException {
		WritableHyperlink link = new WritableHyperlink(cow, row, new File(
				filePath));
		sheet.addHyperlink(link);
	}

	public void setHyperLinkForSheet(int col, int row, String desc,
			String sheetName, int destCol, int destRow)
			throws RowsExceededException, WriteException {
		WritableSheet desSheet = wbe.getSheet(sheetName);
		WritableHyperlink link = new WritableHyperlink(col, row, desc,
				desSheet, destCol, destRow);
		sheet.addHyperlink(link);
	}

	public void copySheet(String modelPath, String destPath)
			throws BiffException, IOException, WriteException {
		WritableWorkbook wb = Workbook.createWorkbook(new File(destPath));
		Workbook model = Workbook.getWorkbook(new File(modelPath));
		Sheet modelSheet = model.getSheet(0);
		wb.importSheet("newsheet", 0, modelSheet);
		wb.write();
		wb.close();

	}

	public void writeTestToExcel(List<Map<String, String>> classData)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		int startRow = sheet.getRows();
		int methodsCounts = classData.size();
		int successCount = 0;
		int failureCount = 0;
		int skippedCount = 0;
		String totalTimeString = sheet.getCell(5, 1).getContents();
		String[] aa = totalTimeString.split("s");
		System.out.println(Float.parseFloat(aa[0]));
		float TotalTime = Float.parseFloat(aa[0]);
		for (int i = 0; i < classData.size(); i++) {
			String className = classData.get(i).get("className");
			String method = classData.get(i).get("method");
			String time = classData.get(i).get("time");
			String status = classData.get(i).get("status");
			String comment = classData.get(i).get("comment");
			String screenPath = classData.get(i).get("screenPath");
			float time1 = Float.parseFloat(time) / 1000;
			TotalTime = TotalTime + Float.parseFloat(time) / 1000;
			time = String.valueOf(time1) + "s";
			if (i == 0) {
				writeLastRow(0, className);

				setHyperLinkForSheet(2, startRow, className + "-log",
						className, 0, 1);
			} else {
				writeLastRow(0, "");
			}

			writeSameRow(3, method);
			writeSameRow(4, time);
			if (screenPath != null) {
				setHyperLinkForFile(5, sheet.getRows() - 1, screenPath);
			}
			writeSameRow(6, comment);
			if (status.equals("Success")) {
				successCount = successCount + 1;
				writeDataByColour(7, sheet.getRows() - 1, status, 10,
						Colour.GREEN);
			}
			if (status.equals("Failure")) {
				failureCount = failureCount + 1;
				writeDataByColour(7, sheet.getRows() - 1, status, 10,
						Colour.RED);
			}
			if (status.equals("Skipped")) {
				skippedCount = skippedCount + 1;
				writeDataByColour(7, sheet.getRows() - 1, status, 10,
						Colour.YELLOW);
			}
		}
		String classSuccessRate = CommonTools.getPercent(successCount,
				methodsCounts);
		writeData(1, startRow, classSuccessRate);
		int endRow = sheet.getRows();
		mergeCells(0, startRow, 0, endRow - 1);
		mergeCells(1, startRow, 1, endRow - 1);
		mergeCells(2, startRow, 2, endRow - 1);
		int oldSuccessCount = Integer.parseInt(sheet.getCell(0, 1)
				.getContents());
		int oldFailureCount = Integer.parseInt(sheet.getCell(1, 1)
				.getContents());
		int oldSkippedCount = Integer.parseInt(sheet.getCell(2, 1)
				.getContents());
		int newSuccessCount = successCount + oldSuccessCount;
		int newFailureCount = failureCount + oldFailureCount;
		int newSiippedCount = skippedCount + oldSkippedCount;
		int total = newSuccessCount + newFailureCount + newSiippedCount;
		writeData(0, 1, String.valueOf(newSuccessCount));
		writeData(1, 1, String.valueOf(newFailureCount));
		writeData(2, 1, String.valueOf(newSiippedCount));
		String allPercent = CommonTools.getPercent(newSuccessCount, total);
		writeData(3, 1, allPercent);
		writeData(4, 1, String.valueOf(total));
		writeData(5, 1, String.format("%.3f", TotalTime) + "s");

	}

	public static void main(String[] args) throws RowsExceededException,
			BiffException, WriteException, IOException {

		// OperateExcel excel = new
		// OperateExcel("/Users/wangxun/Documents/workspace/java/appautotest/ticketIOS.xls",
		// "elements");
		// excel.setColumnView(1, 60);
		// excel.setFormat(19, true);
		/*
		 * excel.writeData(0, 0,
		 * "3434343434343434354354545454545454545454545454545");
		 * excel.setHyperLinkForFile(4,5,"F:/ttt.xls");
		 * excel.setHyperLinkForSheet(5, 5, "5534343", "TestSummary", 6, 6);
		 */
		// excel.copySheet("/Users/wangxun/Documents/workspace/java/appautotest/ticketWeb.xls","/Users/wangxun/Documents/workspace/java/appautotest/ticketIOS.xls");
		// excel.close();
		int a = 3;
		int b = 52;
		float c = b;
		float d = a / c;
		System.out.println(d);
		System.out.println("end");
	}

}
