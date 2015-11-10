package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CommonTools {
	static Properties PROPERTIES = new Properties(System.getProperties());

	public static String getOSName() {
		return PROPERTIES.getProperty("os.name");
	}

	public static String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS");
		return f.format(date);
	}

	public static String getCurrentTime1() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("MMdd-HHmmss");
		return f.format(date);
	}

	public static String setPath(String dirname) {
		String currentPath = System.getProperty("user.dir");
		return currentPath + dirname;
	}

	public static void deleteFile(String filePath) {
		File f = new File(filePath);
		if (f.exists()) {
			f.delete();
		}
	}

	protected static void log(Object content, Integer type) {

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

	public static void log(Object content) {

		log(content, 1);
	}

	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static Properties getConfigFormatData(String configFileName) {
		try {
			Properties pro = new Properties();
			FileInputStream fis = new FileInputStream(configFileName);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader brProp = new BufferedReader(isr);
			pro.load(brProp);
			brProp.close();
			return pro;
		} catch (Exception e) {
			throw new IllegalStateException("Can't locate config file " + configFileName, e);
		}
	}

	public static String getProperties(String configFileName, String name) {

		String currentPath = System.getProperty("user.dir");
		configFileName = currentPath + configFileName;
		Properties properties = getConfigFormatData(configFileName);
		return properties.getProperty(name);

	}

	public static void createSheet(String excelPath, String className, int index)
			throws IOException, WriteException, BiffException {
		Workbook wb = Workbook.getWorkbook(new File(excelPath));
		WritableWorkbook wbe = Workbook.createWorkbook(new File(excelPath), wb);
		className = getValidSheetName(className);
		wbe.createSheet(className, index);
		wbe.write();
		wbe.close();
		wb.close();
	}

	public static void deleteSheet(String excelPath, String name) throws BiffException, IOException, WriteException {
		Workbook wb = Workbook.getWorkbook(new File(excelPath));
		WritableWorkbook wbe = Workbook.createWorkbook(new File(excelPath), wb);
		String[] sheetNames = wbe.getSheetNames();
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < sheetNames.length; i++) {
			String j = Integer.toString(i);
			map.put(sheetNames[i], j);
		}
		for (String sheetName : sheetNames) {
			if (sheetName.contains(name)) {
				wbe.removeSheet(Integer.parseInt(map.get(sheetName)));
			}

		}
		wbe.write();
		wbe.close();
	}
	
	public static String getPercent(int numerator,int denominator){
		float numerator1 = numerator;
		float successRate = numerator1 / denominator;
		DecimalFormat df = new DecimalFormat("0.00%");
		String successRate1 = df.format(successRate);
		return successRate1;
	}

	public static void createWorkbook(String excelDir, String excelName, String className, int index,String projectName,String projectInfo,String testSpecification)
			throws IOException, WriteException, BiffException {

		String navigation[] = { "Success", "Failure ", "Skipped", "Success Rate", "Time Consuming", "Total"};
		String classNavigation[] = { "Test Suite", "Success Rate", "Log", "Test Case", "Time Consuming",
				"Error Screenshot", "Comment", "Status" };
		WritableWorkbook wb = Workbook.createWorkbook(new File(excelDir + excelName));
		className = getValidSheetName(className);
		wb.createSheet(className, index);

		WritableSheet homePageSheet = wb.getSheet(className);
		WritableFont fontTitle = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);
		WritableCellFormat formatTitle = new WritableCellFormat(fontTitle);
		formatTitle.setWrap(true);
		formatTitle.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		WritableFont fontBody = new WritableFont(WritableFont.ARIAL, 10);
		WritableCellFormat formatBody = new WritableCellFormat(fontBody);
		formatBody.setWrap(true);
		formatBody.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		
		Label projectNameLabel = new Label(0, 0, "Project Name", formatTitle);
		Label projectInfoLabel = new Label(0, 1, "Project Info", formatTitle);
		Label testSpecificationLabel = new Label(0, 2, "Test Specification", formatTitle);
		Label projectNameLabel2 = new Label(1, 0, projectName, formatBody);
		Label projectInfoLabel2 = new Label(1, 1, projectInfo, formatBody);
		Label testSpecificationLabel2 = new Label(1, 2, testSpecification, formatBody);
		// 概况 总数量 pass fail skip error 百分百 log
		for (int i = 0; i < navigation.length; i++) {
			homePageSheet.addCell(new Label(i, 3, navigation[i], formatTitle));
		}
		Label label1 = new Label(0, 4, "0", formatBody);
		Label label2 = new Label(1, 4, "0", formatBody);
		Label label3 = new Label(2, 4, "0", formatBody);
		Label label4 = new Label(4, 4, "0s", formatBody);
		homePageSheet.addCell(projectNameLabel);
		homePageSheet.addCell(projectInfoLabel);
		homePageSheet.addCell(testSpecificationLabel);
		homePageSheet.addCell(projectNameLabel2);
		homePageSheet.addCell(projectInfoLabel2);
		homePageSheet.addCell(testSpecificationLabel2);
		homePageSheet.mergeCells(1, 0, 5, 0);
		homePageSheet.mergeCells(1, 1, 5, 1);
		homePageSheet.mergeCells(1, 2, 5, 2);

		homePageSheet.addCell(label1);
		homePageSheet.addCell(label2);
		homePageSheet.addCell(label3);
		homePageSheet.addCell(label4);



		for (int i = 0; i < classNavigation.length; i++) {
			homePageSheet.addCell(new Label(i, 7, classNavigation[i], formatTitle));
		}
		homePageSheet.setColumnView(0, 15);
		homePageSheet.setColumnView(1, 15);
		homePageSheet.setColumnView(2, 15);
		homePageSheet.setColumnView(3, 20);
		homePageSheet.setColumnView(4, 15);
		homePageSheet.setColumnView(5, 15);
		homePageSheet.setColumnView(6, 50);
		wb.write();
		wb.close();
	}

	public static void createXmlforTestNg(String path, Map<String, String> data, String className) {

		XMLWriter output = null;
		Document document = null;

		String methodName = data.get("method");
		String time = data.get("time");
		String comment = data.get("comment");
		String status = data.get("status");
		try {
			// 判断文件是否存在
			if (!new File(path).exists()) {
				// 不存在创建xml 构造class-method 节点.
				output = new XMLWriter(new FileWriter(new File(path)));
				document = DocumentHelper.createDocument();
				// 创建跟节点.
				Element classElement = document.addElement("class");
				classElement.addAttribute("name", className);
				// 添加methods 节点.
				Element methodsElement = classElement.addElement("methods");

				// 添加methods 节点.
				Element methodElement = methodsElement.addElement("method");
				methodElement.addAttribute("name", methodName);
				Element statusElement = methodElement.addElement("status");
				statusElement.setText(status);
				// 添加methods 节点.
				Element timeElement = methodElement.addElement("time");
				timeElement.setText(time);

				Element commentElement = methodElement.addElement("comment");
				commentElement.setText(comment);

			} else {
				SAXReader reader = new SAXReader();
				try {
					document = reader.read(path);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Element root = document.getRootElement();
				Element methods = root.element("methods");
				Element methodElement = methods.addElement("method");
				methodElement.addAttribute("name", methodName);
				Element statusElement = methodElement.addElement("status");
				statusElement.setText(status);
				Element timeElement = methodElement.addElement("time");
				timeElement.setText(time);
				Element commentElement = methodElement.addElement("comment");
				commentElement.setText(comment);

			}
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			output = new XMLWriter(new FileWriter(new File(path)), format);
			output.write(document);
			output.close();
			System.out.println("-----------------------------------------------------------");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static List<Map<String, String>> readXmlForTestNg(String path) {
		List<Map<String, String>> classData = new ArrayList<Map<String, String>>();
		Document document = null;
		SAXReader reader = new SAXReader();
		try {
			document = reader.read(path);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		String className = root.attribute("name").getValue();
		System.out.println(className + "33333333333333333333333333");
		Element methods = root.element("methods");
		for (int i = 0; i < methods.nodeCount(); i++) {
			Map<String, String> methodData = new HashMap<String, String>();
			Node node = methods.node(i);
			if (node instanceof Element) {
				Element method = (Element) node;

				Element statusElement = method.element("status");
				Element timeElement = method.element("time");
				Element commentElement = method.element("comment");
				String methodName = method.attribute("name").getValue();
				String status = statusElement.getStringValue();
				String time = timeElement.getStringValue();
				String comment = commentElement.getStringValue();
				methodData.put("className", className);
				methodData.put("method", methodName);
				methodData.put("time", time);
				methodData.put("status", status);
				methodData.put("comment", comment);
				classData.add(methodData);
			}
		}
		return classData;
	}
	
	public static String getValidSheetName(String sheetName){
		if(sheetName.length()>31){
			return sheetName.substring(sheetName.length()-31, sheetName.length());
		}
		return sheetName;
	}

	public void copyFile(String oldPath, String newPath) { 
		try { 
		int bytesum = 0; 
		int byteread = 0; 
		File oldfile = new File(oldPath); 
		if (oldfile.exists()) { //文件存在时 
		InputStream inStream = new FileInputStream(oldPath); //读入原文件 
		FileOutputStream fs = new FileOutputStream(newPath); 
		byte[] buffer = new byte[1444]; 
		while ( (byteread = inStream.read(buffer)) != -1) { 
		bytesum += byteread; //字节数 文件大小 
		System.out.println(bytesum); 
		fs.write(buffer, 0, byteread); 
		fs.close();
		} 
		inStream.close(); 
		} 
		} 
		catch (Exception e) { 
		System.out.println("复制单个文件操作出错"); 
		e.printStackTrace(); 

		} 

		} 
	
	public static void main(String[] args) throws RowsExceededException, BiffException, WriteException, IOException {
		
		
		
		
		System.out.println("end");
	}

}
