package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import core.Initial;


public class ListenersTestng extends TestListenerAdapter {

    public void onTestFailure(ITestResult tr) {

        String url = tr.getTestContext().getOutputDirectory();

        Map<String, String> data = new HashMap<String, String>();
        String className = "";
        String method = "";
        long time = 0;
        String status = "";
        String message = "";
        // get class name.
        className = tr.getTestClass().getName();
        String[] strs = className.split("\\.");
        className = strs[strs.length - 1];

        // get method name.
        method = tr.getName();

        // get the method run time.
        time = tr.getEndMillis() - tr.getStartMillis();

        // get the method run result.
        try {
            message = tr.getThrowable().getMessage();
        	if(message.isEmpty()){
                message = "message is null";
        	}
        } catch (Exception e) {

            message = "message is null";
        	
        }
        status = "FAILURE";

        data.put("ClassName", className);
        data.put("method", method);
        data.put("time", time + "");
        data.put("status", status);
        data.put("comment", formatDate(System.currentTimeMillis()) + " - Failed " + message);
        System.out.println(data.get("comment"));
        createXml("/Users/wangxun/Documents/workspace/java/appautotest/testresource/" + className
                + ".xml", data, className);

    }

    public void onTestSuccess(ITestResult tr) {
        super.onTestFailure(tr);

        String url = tr.getTestContext().getOutputDirectory();
        Map<String, String> data = new HashMap<String, String>();

        String className = "";
        String method = "";
        long time = 0;
        String status = "";
        String message = "";
        // get class name.
        className = tr.getTestClass().getName();
        String[] strs = className.split("\\.");
        className = strs[strs.length - 1];
        // get method name.
        method = tr.getName();

        // get the method run time.
        time = tr.getEndMillis() - tr.getStartMillis();

        try {
            message = tr.getThrowable().getMessage();
            message = Initial.successMessage;
            if(message.isEmpty()){
            	message = "The success message is null.";
            }
        } catch (Exception e) {

            message = "The success message is null.";

            
        }
        status = "SUCCESS";
        data.put("ClassName", className);
        data.put("method", method);
        data.put("time", time + "");
        data.put("status", status);
        data.put("comment", message);
        System.out.println(data.get("comment"));

        createXml("/Users/wangxun/Documents/workspace/java/appautotest/testresource/" + className
                + ".xml", data, className);
    }

    public void onTestSkipped(ITestResult tr) {
        super.onTestFailure(tr);
        String url = tr.getTestContext().getOutputDirectory();

        Map<String, String> data = new HashMap<String, String>();
        String className = "";
        String method = "";
        long time = 0;
        String status = "";
        String message = "";
        // get class name.
        className = tr.getTestClass().getName();
        String[] strs = className.split("\\.");
        className = strs[strs.length - 1];

        // get method name.
        method = tr.getName();

        // get the method run time.
        time = tr.getEndMillis() - tr.getStartMillis();

        try {
            message = tr.getThrowable().getMessage();

        } catch (Exception e) {
            message = "This Methis IS SKIP.";
        }

        status = "SKIP";
        data.put("ClassName", className);
        data.put("method", method);
        data.put("time", time + "");
        data.put("status", status);
        data.put("comment", message);
        System.out.println(data.get("comment"));
        createXml("/Users/wangxun/Documents/workspace/java/appautotest/testresource/" + className
                + ".xml", data, className);

    }

    private void createXml(String path, Map<String, String> data, String className) {

        XMLWriter output = null;
        Document document = null;

        String methodName = data.get("method");
        String time = data.get("time");
        String comment = data.get("comment");
        String pass = "0";
        String fail = "0";
        String skip = "0";
        String error = "0";
        String status = data.get("status");

        if (status.equals("SUCCESS")) {
            pass = "1";
        } else if (status.equals("FAILURE")) {
            fail = "1";
        } else if (status.equals("SKIP")) {
            skip = "1";
        } else if (status.isEmpty()) {
            error = "1";
        }

        try {
            // 判断文件是否存在
            if (!new File(path).exists()) {
                // 不存在创建xml 构造class-method 节点.
                output = new XMLWriter(new FileWriter(new File(path)));
                document = DocumentHelper.createDocument();
                // 创建跟节点.
                Element classesElement = document.addElement("classes");
                Element classElement = classesElement.addElement("class");
                classElement.addAttribute("name", className);
                // 添加methods 节点.
                Element methodsElement = classElement.addElement("methods");

                // 添加methods 节点.
                Element methodElement = methodsElement.addElement("method");
                methodElement.addAttribute("name", methodName);

                // 添加methods 节点.
                Element passElement = methodElement.addElement("pass");
                passElement.setText(pass);

                // 添加methods 节点.
                Element failElement = methodElement.addElement("fail");
                failElement.setText(fail);
                // 添加methods 节点.
                Element skipElement = methodElement.addElement("skip");
                skipElement.setText(skip);
                // 添加methods 节点.
                Element errorElement = methodElement.addElement("error");
                errorElement.setText(error);
                // 添加methods 节点.
                Element timeElement = methodElement.addElement("time");
                timeElement.setText(time);

                Element commentElement = methodElement.addElement("comment");
                commentElement.setText(comment);

                output.write(document);
                output.flush();
                output.close();

            } else {
                // excel 存在 在class 节点里面添加method
                document = readerTestData(path);
                Element rootElement = document.getRootElement();
                @SuppressWarnings("unchecked")
                List<Element> elements = rootElement.selectNodes("//class");

                for (int i = 0; i < elements.size(); i++) {
                    Element element = elements.get(i);
                    Element addToElement = (Element) element.selectNodes("./methods").get(0);
                    String name = element.attribute("name").getText().toString();
                    if (name.equals(className)) {
                        // 创建 mehotd 节点,添加属性 name = value
                        Element methodElemen = createNewsElementAndSetVal("method", "name",
                                methodName);

                        // 添加 节点 pass 并写入text 文本值.
                        Element passElement = createNewsElementAndsetText("pass", pass);

                        // 添加 节点 fail 并写入text 文本值.
                        Element failElement = createNewsElementAndsetText("fail", fail);

                        // 添加 节点 skip 并写入text 文本值.
                        Element skipElement = createNewsElementAndsetText("skip", skip);

                        // 添加 节点 error 并写入text 文本值.
                        Element errorElement = createNewsElementAndsetText("error", error);

                        // 添加 节点 time 并写入text 文本值.
                        Element timeElement = createNewsElementAndsetText("time", time);

                        // 添加 节点 comment 并写入text 文本值.
                        Element commentElement = createNewsElementAndsetText("comment", comment);

                        addElement(addToElement, methodElemen);
                        addElement(methodElemen, passElement);
                        addElement(methodElemen, failElement);
                        addElement(methodElemen, skipElement);
                        addElement(methodElemen, errorElement);
                        addElement(methodElemen, timeElement);
                        addElement(methodElemen, commentElement);
                        break;
                    }
                }

                OutputFormat format = OutputFormat.createPrettyPrint();
                format.setEncoding("UTF-8");
                output = new XMLWriter(new FileWriter(new File(path)), format);
                output.write(document);
                output.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Document readerTestData(String XmlPath) {

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

    private Element createNewsElementAndSetVal(String elementName, String key, String value) {
        return DocumentHelper.createElement(elementName).addAttribute(key, value);
    }

    private Element createNewsElementAndsetText(String elementName, String text) {

        Element element = DocumentHelper.createElement(elementName);
        element.setText(text);
        return element;
    }

    private void addElement(Element parentNode, Element childNode) {

        parentNode.add(childNode);
    }

    /*
     * public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
     * String outputDirectory) {
     * 
     * System.out.println("Run 1+ 1"); List<ITestResult> list = new
     * ArrayList<ITestResult>(); int i = 0; for (ISuite suite : suites) {
     * 
     * Map<String, ISuiteResult> suiteResults = suite.getResults(); for
     * (ISuiteResult suiteResult : suiteResults.values()) { ITestContext
     * testContext = suiteResult.getTestContext();
     * 
     * IResultMap passedTests = testContext.getPassedTests(); IResultMap
     * failedTests = testContext.getFailedTests(); IResultMap skippedTests =
     * testContext.getSkippedTests(); IResultMap failedConfig =
     * testContext.getFailedConfigurations();
     * 
     * list.addAll(this.listTestResult(passedTests));
     * list.addAll(this.listTestResult(failedTests));
     * list.addAll(this.listTestResult(skippedTests));
     * list.addAll(this.listTestResult(failedConfig));
     * 
     * } } this.sort(list); // this.outputResult(list,
     * outputDirectory.replace("test-output", // "userDefinedLog") +
     * "\\testData.xml"); }
     * 
     * private ArrayList<ITestResult> listTestResult(IResultMap resultMap) {
     * Set<ITestResult> results = resultMap.getAllResults(); return new
     * ArrayList<ITestResult>(results); }
     * 
     * private void sort(List<ITestResult> list) { Collections.sort(list, new
     * Comparator<ITestResult>() { public int compare(ITestResult r1,
     * ITestResult r2) { if (r1.getStartMillis() > r2.getStartMillis()) { return
     * 1; } else { return -1; } } }); }
     * 
     * private void outputResult(List<ITestResult> list, String path) {
     * 
     * BufferedWriter output;
     * 
     * List<Map<String, String>> testData = new ArrayList<Map<String,
     * String>>();
     * 
     * for (ITestResult result : list) { Map<String, String> data = new
     * HashMap<String, String>(); String className =
     * result.getTestClass().getRealClass().getName(); String method =
     * result.getMethod().getMethodName(); long time = result.getEndMillis() -
     * result.getStartMillis(); String status = getStatus(result.getStatus());
     * 
     * System.out.println(className + " " + method + " " + time + " " + status +
     * mehtod_Comment.get(method)); data.put("ClassName", className);
     * data.put("method", method); data.put("time", time + "");
     * data.put("status", status); data.put("comment",
     * mehtod_Comment.get(method)); testData.add(data);
     * 
     * sb.append(result.getTestClass().getRealClass().getName()).append(" " )
     * .append(result.getMethod().getMethodName()).append(" ")
     * .append(this.formatDate(result.getStartMillis())).append(" ")
     * .append(result.getEndMillis() - result.getStartMillis()).append("毫秒 ")
     * .append(this.getStatus(result.getStatus()));
     * 
     * 
     * }
     * 
     * // createXml(path, testData);
     * 
     * }
     */
    /*
     * private String getStatus(int status) { String statusString = null; switch
     * (status) { case 1: statusString = "SUCCESS"; break; case 2: statusString
     * = "FAILURE"; break; case 3: statusString = "SKIP"; break; default: break;
     * } return statusString; }
     */

    private String formatDate(long date) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        return formatter.format(date);
    }

}