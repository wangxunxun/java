package utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
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

	public OperateExcel(String excelPath, String className) throws BiffException, IOException {

		wb = Workbook.getWorkbook(new File(excelPath));
		wbe = Workbook.createWorkbook(new File(excelPath), wb);
		sheet = wbe.getSheet(className);

	}

	public void writeLastRow(int cow, Object content)
			throws RowsExceededException, WriteException, BiffException, IOException {

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
			throws RowsExceededException, WriteException, BiffException, IOException {

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
			throws RowsExceededException, WriteException, BiffException, IOException {

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

	public void mergeCells(int col1, int row1, int col2, int row2) throws RowsExceededException, WriteException {
		sheet.mergeCells(col1, row1, col2, row2);
	}

	public void setColumnView(int col, int width) {
		sheet.setColumnView(col, width);
	}

	public void writeTitle(int cow, int row, String content, int size) throws RowsExceededException, WriteException {
		WritableFont font = new WritableFont(WritableFont.ARIAL, size, WritableFont.BOLD);
		WritableCellFormat format = new WritableCellFormat(font);
		Label label = new Label(cow, row, content, format);
		sheet.addCell(label);
	}

	public void writeDataByColour(int cow, int row, String content, int size, Colour colour)
			throws RowsExceededException, WriteException {
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
		WritableHyperlink link = new WritableHyperlink(cow, row, new File(filePath));
		sheet.addHyperlink(link);
	}

	public void setHyperLinkForSheet(int col, int row, String desc, String sheetName, int destCol, int destRow)
			throws RowsExceededException, WriteException {
		WritableSheet desSheet = wbe.getSheet(sheetName);
		WritableHyperlink link = new WritableHyperlink(col, row, desc, desSheet, destCol, destRow);
		sheet.addHyperlink(link);
	}

	public void copySheet(String modelPath, String destPath) throws BiffException, IOException, WriteException {
		WritableWorkbook wb = Workbook.createWorkbook(new File(destPath));
		Workbook model = Workbook.getWorkbook(new File(modelPath));
		Sheet modelSheet = model.getSheet(0);
		wb.importSheet("newsheet", 0, modelSheet);
		wb.write();
		wb.close();

	}

	public void writeTestToExcel(List<Map<String, String>> classData)
			throws RowsExceededException, WriteException, BiffException, IOException {

		int startRow = sheet.getRows();
		int methodsCounts = classData.size();
		int successCount = 0;
		int failureCount = 0;
		int skippedCount = 0;
		float successRate;
		for (int i = 0; i < classData.size(); i++) {
			String className = classData.get(i).get("className");
			String method = classData.get(i).get("method");
			String time = classData.get(i).get("time");
			String status = classData.get(i).get("status");
			String comment = classData.get(i).get("comment");
			String screenPath = classData.get(i).get("screenPath");
			float time1 = Float.parseFloat(time) / 1000;
			time = String.valueOf(time1) + "s";
			if (i == 0) {
				writeLastRow(0, className + "(" + CommonTools.getCurrentTime() + ")");

				setHyperLinkForSheet(2, startRow, className + "-log", className, 0, 1);
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
				writeDataByColour(7, sheet.getRows() - 1, status, 10, Colour.GREEN);
			}
			if (status.equals("Failure")) {
				failureCount = failureCount + 1;
				writeDataByColour(7, sheet.getRows() - 1, status, 10, Colour.RED);
			}
			if (status.equals("Skipped")) {
				skippedCount = skippedCount + 1;
				writeDataByColour(7, sheet.getRows() - 1, status, 10, Colour.YELLOW);
			}
		}
		float successCount2 = successCount;
		successRate = successCount2 / methodsCounts;
		DecimalFormat df = new DecimalFormat("0.00%");
		String successRate1 = df.format(successRate);
		writeData(1, startRow, successRate1);
		int endRow = sheet.getRows();
		mergeCells(0, startRow, 0, endRow - 1);
		mergeCells(1, startRow, 1, endRow - 1);
		mergeCells(2, startRow, 2, endRow - 1);
		int oldSuccessCount = Integer.parseInt(sheet.getCell(0, 1).getContents());
		int oldFailureCount = Integer.parseInt(sheet.getCell(1, 1).getContents());
		int oldSkippedCount = Integer.parseInt(sheet.getCell(2, 1).getContents());
		int newSuccessCount = successCount+oldSuccessCount;
		int newFailureCount = failureCount+oldFailureCount;
		int newSiippedCount = skippedCount+oldSkippedCount;
		writeData(0, 1, String.valueOf(newSuccessCount));
		writeData(1, 1, String.valueOf(newFailureCount));
		writeData(2, 1, String.valueOf(newSiippedCount));
		
		
	}

	public int getTestsValue(int cel, int row) {

		String value = sheet.getCell(cel, row).getContents();
		if (value.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(value);
		}

	}

	public void setValueToSummary(String pass, String fail, String skip, String error, String time) {

		try {
			int SMPass = getTestsValue(2, 2) + Integer.parseInt(pass);
			int SMFail = getTestsValue(3, 2) + Integer.parseInt(fail);
			int SMSkip = getTestsValue(4, 2) + Integer.parseInt(skip);
			;
			int SMError = getTestsValue(5, 2) + Integer.parseInt(error);
			int SMTime = getTestsValue(6, 2) + Integer.parseInt(time);

			Label passLabel = new Label(2, 2, SMPass + "");
			Label failLabel = new Label(3, 2, SMFail + "");
			Label skipLabel = new Label(4, 2, SMSkip + "");
			Label errorLabel = new Label(5, 2, SMError + "");
			Label timeLabel = new Label(7, 2, SMTime + "");

			sheet.addCell(passLabel);
			sheet.addCell(failLabel);
			sheet.addCell(skipLabel);
			sheet.addCell(errorLabel);
			sheet.addCell(timeLabel);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void readXML(String path) {
		// 当前class 总共的method 个数.
		int countTests = 0;
		// class name
		String className = "";

		Document document = readerTestData(path);
		Element rootElement = document.getRootElement();

		List<Element> classes = rootElement.selectNodes("//class");

		if (classes.size() != 1) {
			throw new IndexOutOfBoundsException("一个xml文件只能保存一个class 的信息.");
		}
		Element currentClassElement = classes.get(0);
		className = currentClassElement.attribute("name").getValue().toString();
		// 解析method

		List<Element> methods = currentClassElement.selectNodes("./methods/method");
		countTests = methods.size();

		int row = 0;
		int cloumns = 0;
		for (int i = 0; i < methods.size(); i++) {
			try {
				Map<String, String> result = new HashMap<String, String>();
				String methodName = "";
				String time = "";
				String comment = "";
				String pass = "0";
				String fail = "0";
				String skip = "0";
				String error = "0";
				String status = "";
				// 获取method 值

				Element methodElement = (Element) methods.get(i);
				methodName = methodElement.attribute("name").getValue().toString();
				// 获取 pass值.
				Element passElement = (Element) methods.get(i).selectNodes("./pass").get(0);
				pass = passElement.getText();
				// 获取 fail值.
				Element failElement = (Element) methods.get(i).selectNodes("./fail").get(0);
				fail = failElement.getText();
				// 获取 skip值.
				Element skipElement = (Element) methods.get(i).selectNodes("./skip").get(0);
				skip = skipElement.getText();
				// 获取 error值.
				Element errorElement = (Element) methods.get(i).selectNodes("./error").get(0);
				error = errorElement.getText();
				// 获取 time值.
				Element timeElement = (Element) methods.get(i).selectNodes("./time").get(0);
				time = timeElement.getText();
				// 获取 comment值.
				Element commentElement = (Element) methods.get(i).selectNodes("./comment").get(0);
				comment = commentElement.getText();

				// 把值写入到Excel

				// open excel.
				row = sheet.getRows();
				cloumns = sheet.getColumns();

				Label label1 = new Label(0, row, className);
				Label label2 = new Label(1, row, methodName);
				Label label3 = new Label(2, row, pass);
				Label label4 = new Label(3, row, fail);
				Label label5 = new Label(4, row, skip);
				Label label6 = new Label(5, row, error);
				Label label7 = new Label(7, row, time);
				// Label label8 = new Label(8, row, "log" );
				Label lable10 = new Label(10, row, comment);

				setValueToSummary(pass, fail, skip, error, time);
				// setAddress("test1",label8 );

				sheet.addCell(label1);
				sheet.addCell(label2);
				sheet.addCell(label3);
				sheet.addCell(label4);
				sheet.addCell(label5);
				sheet.addCell(label6);
				sheet.addCell(label7);
				sheet.addCell(lable10);
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			className = "";
		}
		WritableCellFormat headerFormat = new WritableCellFormat();
		try {
			headerFormat.setAlignment(Alignment.CENTRE);
			headerFormat.setBackground(Colour.BLUE);

			String logText = className + "_Log";
			Label label8 = new Label(8, row - countTests + 1, logText, headerFormat);

			setAddress(logText, label8, wbe.getSheet(0), countTests);

			sheet.addCell(label8);

			int tests = getTestsValue(1, 2);
			Label testsLabel = new Label(1, 2, (tests + countTests) + "");
			sheet.addCell(testsLabel);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Document readerTestData(String XmlPath) {

		try {
			File file = new File(XmlPath);
			if (!file.exists()) {
				if (!file.exists()) {
					throw new RuntimeException("Test data set file: [" + file + "] "
							+ "could not be found, please make sure you set property correctly.");
				}
			}
			SAXReader reader = new SAXReader();
			return reader.read(XmlPath);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void setAddress(String labelText, Label label, WritableSheet sheet, int count) {

		try {
			int column = label.getColumn();
			int row = label.getRow();
			WritableHyperlink link = new WritableHyperlink(column, row, labelText, sheet, 1, 2);
			sheet.addHyperlink(link);
			sheet.mergeCells(column, row, column, row + count - 1);

		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws RowsExceededException, BiffException, WriteException, IOException {

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
