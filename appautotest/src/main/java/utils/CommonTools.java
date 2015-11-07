package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
import jxl.read.biff.BiffException;
import jxl.write.Label;
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

	public static void createWorkbook(String excelDir, String excelName, String className, int index)
			throws IOException, WriteException, BiffException {

		String navigation[] = { "Summary", "Test ", "Pass", "Failed", "Skip", "Success rate", "Time Consuming" };
		String classNavigation[] = { "Test Suite", "Success Rate", "Log", "Test Case", "Time Consuming",
				"Error Screenshot", "Comment", "Status" };
		WritableWorkbook wb = Workbook.createWorkbook(new File(excelDir + excelName));
		wb.createSheet(className, index);

		WritableSheet homePageSheet = wb.getSheet(className);
		Label label = new Label(0, 0, "Description");
		homePageSheet.addCell(label);

		// 概况 总数量 pass fail skip error 百分百 log
		for (int i = 0; i < navigation.length; i++) {
			homePageSheet.addCell(new Label(i, 1, navigation[i]));
		}

		for (int i = 0; i < classNavigation.length; i++) {
			homePageSheet.addCell(new Label(i, 3, classNavigation[i]));
		}
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

	public static void main(String[] args) throws RowsExceededException, BiffException, WriteException, IOException {

		Map<String, String> methodData = new HashMap<String, String>();
		methodData.put("method", "sadas实打实");
		methodData.put("status", "pass");
		methodData.put("time", "333");
		methodData.put("comment", "commentsssdfdf");

		Map<String, String> methodData1 = new HashMap<String, String>();
		methodData1.put("method", "sfdsf的份上");
		methodData1.put("status", "pass3");
		methodData1.put("time", "3333");
		methodData1.put("comment", "commentsssdfdf3");

		Map<String, String> methodData2 = new HashMap<String, String>();
		methodData2.put("method", "sfdsf的份上");
		methodData2.put("status", "pass3");
		methodData2.put("time", "3333");
		methodData2.put("comment", "commentsssdfdf3");

		List<Map<String, String>> data = readXmlForTestNg("F:/workspace/java/appautotest/testResource/333.xml");
		System.out.println(data.size());
		System.out.println("end");
	}

}
