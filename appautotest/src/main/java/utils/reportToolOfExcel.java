package utils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class reportToolOfExcel {

    protected static Workbook wb;
    protected static WritableWorkbook wbe;
    protected static WritableSheet sheet;
    private static String navigation[] = { "Summary", "Tests", "Pass", "Failed", "Skip", "Errors", "Success rate", "Time" };
    private static String classNavigation[] = { "Classes", "MethodName", "Pass", "Failed", "Skip", "Errors", "Success rate", "Time ", "log",
            "Error Screenshot", "Comment" };

    public static void createWorkbook(String excelPath, String sheetName, int index) {

        try {
            if (!new File(excelPath).exists()) {
                WritableWorkbook wb = Workbook.createWorkbook(new File(excelPath));
                wb.createSheet(sheetName, index);
                WritableSheet homePageSheet = wb.getSheet(sheetName);

                Label label = new Label(0, 0, "Description", addStyle(15, true, true, false, "periwinkle"));
                homePageSheet.addCell(label);
                setCellSize(homePageSheet, 0, 0, 10, 600);
                // 概况 总数量 pass fail skip error 百分百 log
                for (int i = 0; i < navigation.length; i++) {

                    String ys = "periwinkle";
                    if (i == 1) {
                        ys = "yellow";
                    } else if (i == 2) {
                        ys = "green";
                    } else if (i == 3) {
                        ys = "red";
                    } else if (i == 4) {
                        ys = "gold";
                    } else if (i == 5) {
                        ys = "darkRed";
                    }

                    homePageSheet.addCell(new Label(i, 1, navigation[i], addStyle(12, true, true, false, ys)));
                    setCellSize(homePageSheet, i, 1, 8, 600);
                }
                for (int i = 0; i < classNavigation.length; i++) {
                    String ys = "periwinkle";
                    if (i == 2) {
                        ys = "green";
                    } else if (i == 3) {
                        ys = "red";
                    } else if (i == 4) {
                        ys = "gold";
                    } else if (i == 5) {
                        ys = "darkRed";
                    }
                    homePageSheet.addCell(new Label(i, 3, classNavigation[i], addStyle(10, true, true, false, ys)));
                    setCellSize(homePageSheet, i, 3, 8, 600);

                }
                wb.write();
                wb.close();
            }

        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
    }

    public static void createSheet(String excelPath, String sheetName, String linkSheetName, int index) {
        Workbook wb;
        try {
            wb = Workbook.getWorkbook(new File(excelPath));
            WritableWorkbook wbe = Workbook.createWorkbook(new File(excelPath), wb);
            wbe.createSheet(sheetName, index);
            WritableSheet desSheet = wbe.getSheet(linkSheetName);

            WritableHyperlink link = new WritableHyperlink(0, 0, "Back", desSheet, 0, 0);
            wbe.getSheet(sheetName).addHyperlink(link);
            wbe.write();
            wbe.close();
            wb.close();
        } catch (BiffException | WriteException | IOException e) {
            e.printStackTrace();
        }
    }

    // get Sheet.
    public static void openSheet(String excelPath, String sheetName) {

        try {
            wb = Workbook.getWorkbook(new File(excelPath));

            wbe = Workbook.createWorkbook(new File(excelPath), wb);
            sheet = wbe.getSheet(sheetName);
        } catch (BiffException | IOException e) {

            e.printStackTrace();
        }
    }

    public static void writeLastRow(int cow, Object content) {

        try {
            int row = sheet.getRows();
            Label lbl = new Label(cow, row, (String) content);
            sheet.addCell(lbl);
        } catch (WriteException e) {

            e.printStackTrace();
        }
    }

    public static void writeSameRow(int cow, Object content) {

        try {
            int row = sheet.getRows();
            Label lbl = new Label(cow, row - 1, (String) content);
            sheet.addCell(lbl);
        } catch (WriteException e) {

            e.printStackTrace();
        }
    }

    public static void writeData(int cow, int row, Object content) {

        try {
            Label lbl = new Label(cow, row, (String) content);
            sheet.addCell(lbl);
        } catch (WriteException e) {

            e.printStackTrace();
        }
    }

    public static void writeData(int cow, int row, Object content, WritableCellFormat wcf) {

        try {
            Label lbl = new Label(cow, row, (String) content, wcf);
            sheet.addCell(lbl);
        } catch (WriteException e) {

            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            wbe.write();
            wbe.close();
        } catch (IOException | WriteException e) {

            e.printStackTrace();
        }

    }

    public static int getTestsValue(int cel, int row) {

        String value = sheet.getCell(cel, row).getContents();
        if (value.isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(value);
        }

    }

    public static double getTestsValueDouble(int cel, int row) {

        String value = sheet.getCell(cel, row).getContents().replace("s", "");
        if (value.isEmpty()) {
            return 0;
        } else {
            return Double.parseDouble(value);
        }

    }

    public static void setValueToSummary(String pass, String fail, String skip, String error, String time) {

        DecimalFormat df = new DecimalFormat("######0.00");
        // 得到当前Summary 信息 + class 统计后的信息.
        int SMPass = getTestsValue(2, 2) + Integer.parseInt(pass);
        int SMFail = getTestsValue(3, 2) + Integer.parseInt(fail);
        int SMSkip = getTestsValue(4, 2) + Integer.parseInt(skip);

        int SMError = getTestsValue(5, 2) + Integer.parseInt(error);
        double SMTime = getTestsValueDouble(7, 2) + Double.parseDouble(time);

        writeData(2, 2, SMPass + "", addStyle(14, true, true, true, "white"));
        writeData(3, 2, SMFail + "", addStyle(14, true, true, true, "white"));
        writeData(4, 2, SMSkip + "", addStyle(14, true, true, true, "white"));
        writeData(5, 2, SMError + "", addStyle(14, true, true, true, "white"));
        writeData(7, 2, df.format(SMTime) + "s", addStyle(14, true, true, true, "white"));

    }

    @SuppressWarnings({ "unchecked", "unused" })
    public static void readXML(String path) {
        // 解析xml
        Document document = readerTestData(path);
        Element rootElement = document.getRootElement();

        List<Element> classes = rootElement.selectNodes("//class");
        if (classes.size() != 1) {
            throw new IndexOutOfBoundsException("一个xml文件只能保存一个class 的信息.");
        }
        Element currentClassElement = classes.get(0);
        // class name
        String className = currentClassElement.attribute("name").getValue().toString();
        // 解析method
        List<Element> methods = currentClassElement.selectNodes("./methods/method");
        // 当前class 总共的method 个数.
        int countTests = methods.size();

        int row = 0;
        int cloumns = 0;
        // 统计当前class method 通过的个数。
        int rateMethod = 0;
        for (int i = 0; i < methods.size(); i++) {

            Map<String, String> result = new HashMap<String, String>();
            String methodName = "", time = "", comment = "", image = "", pass = "0", fail = "0", skip = "0", error = "0", status = "";

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
            time = Integer.parseInt(timeElement.getText()) / 1000 + "." + Integer.parseInt(timeElement.getText()) % 1000;

            // 获取 comment值.
            Element commentElement = (Element) methods.get(i).selectNodes("./comment").get(0);
            comment = commentElement.getText();
            // 获取image值
            Element imageElement = (Element) methods.get(i).selectNodes("./image").get(0);
            image = imageElement.getText();

            if (pass.equals("1")) {
                rateMethod++;
            }
            // 把值写入到Excel
            row = sheet.getRows();
            cloumns = sheet.getColumns();

            writeData(0, row, className);
            writeData(1, row, methodName);
            writeData(2, row, pass);
            writeData(3, row, fail);
            writeData(4, row, skip);
            writeData(5, row, error);
            writeData(7, row, time + "s");

            setLinkToErrorScrenshot(9, row, image);
            writeData(10, row, comment);

            // 每写一个class 信息的时候 同时Summary 也会同步, 第一次写入class 信息时候，
            // 得到当前Summary信息相应加上新class 的信息， 最后得到的是Summary信息.
            setValueToSummary(pass, fail, skip, error, time);

            className = "";
        }

        // log link-- current class
        className = currentClassElement.attribute("name").getValue().toString();
        setHyperLinkForSheet(8, row - countTests + 1, className + "_Log", className, 1, 2);

        // success Rate -- current class
        writeData(6, row - countTests + 1, (rateMethod * 100 / countTests) + "%");

        // tests -- summary
        int tests = getTestsValue(1, 2);
        writeData(1, 2, (tests + countTests) + "", addStyle(14, true, true, true, "white"));
        // Success Rate -- summary
        int passed = getTestsValue(2, 2);
        String successRate = (passed * 100 / (tests + countTests)) + "%";
        writeData(6, 2, successRate, addStyle(14, true, true, true, "white"));

    }

    public static void setLinkToErrorScrenshot(int col, int row, String imagePath) {
        try {
            if (!imagePath.equals("null")) {
                System.out.println("image path : " + imagePath);
                File file = new File(imagePath);
                WritableHyperlink link = new WritableHyperlink(col, row, file);

                sheet.addHyperlink(link);
            }

        } catch (WriteException e) {

            e.printStackTrace();
        }
    }

    public static Document readerTestData(String XmlPath) {

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

    public static void setHyperLinkForSheet(int col, int row, String desc, String sheetName, int destCol, int destRow) {

        try {
            WritableSheet desSheet = wbe.getSheet(sheetName);
            WritableHyperlink link = new WritableHyperlink(col, row, desc, desSheet, destCol, destRow);
            sheet.addHyperlink(link);
        } catch (WriteException e) {

            e.printStackTrace();
        }

    }

    public static void deleteSheet(String excelPath, String name) {

        try {

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
            wb.close();
        } catch (BiffException | IOException | WriteException e) {

            e.printStackTrace();
        }

    }

    public static void writeContent(String excelPath, String className, int cow, int row, Object content) {

        try {
            Workbook wb = Workbook.getWorkbook(new File(excelPath));
            WritableWorkbook wbe = Workbook.createWorkbook(new File(excelPath), wb);
            WritableSheet sheet = wbe.getSheet(className);
            Label lbl = new Label(cow, row, (String) content);
            sheet.addCell(lbl);
            wbe.write();
            wbe.close();
        } catch (BiffException | IOException | WriteException e) {

            e.printStackTrace();
        }

    }

    public static void writeLastRow(String excelPath, String className, int cow, Object content) {

        try {
            Workbook wb = Workbook.getWorkbook(new File(excelPath));
            WritableWorkbook wbe = Workbook.createWorkbook(new File(excelPath), wb);
            WritableSheet sheet = wbe.getSheet(className);
            int row = sheet.getRows();
            Label lbl = new Label(cow, row, (String) content);
            sheet.addCell(lbl);
            wbe.write();
            wbe.close();
        } catch (BiffException | IOException | WriteException e) {

            e.printStackTrace();
        }

    }

    public static WritableCellFormat addStyle(int fontSize, boolean isBold, boolean isCenter, boolean isWrap, String bgColor) {

        try {
            WritableFont headFont = null;
            // 设置字体
            if (isBold) {
                headFont = new WritableFont(WritableFont.TIMES, fontSize, WritableFont.BOLD);
            } else {
                headFont = new WritableFont(WritableFont.TIMES, fontSize, WritableFont.NO_BOLD);
            }

            WritableCellFormat cell = new WritableCellFormat(headFont);
            if (isCenter) {
                cell.setAlignment(Alignment.CENTRE);
                cell.setVerticalAlignment(VerticalAlignment.CENTRE);// 单元格内容垂直居中.
            } else {
                cell.setAlignment(Alignment.LEFT);
                cell.setVerticalAlignment(VerticalAlignment.CENTRE);// 单元格内容垂直居中.
            }

            cell.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK); // 边框
            // 是否换行
            cell.setWrap(isWrap);
            WritableCellFormat wcf = new WritableCellFormat(cell);// 单元格样式.
            wcf.setBackground(getColour(bgColor));
            return wcf;
        } catch (WriteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    public static void setCellSize(WritableSheet sheet, int col, int row, int x, int y) {
        try {
            String value = sheet.getCell(col, row).getContents().toString();
            sheet.setColumnView(col, new String(value).length() + x);

            sheet.setRowView(col, row + y, false);
        } catch (RowsExceededException e) {
            e.printStackTrace();
        }
    }

    public static Colour getColour(String ys) {
        Map<String, Colour> colours = new HashMap<String, Colour>();

        colours.put("unknown", Colour.UNKNOWN);
        colours.put("black", Colour.BLACK);
        colours.put("white", Colour.WHITE);
        colours.put("defaultBackground", Colour.DEFAULT_BACKGROUND);
        colours.put("red", Colour.RED);
        colours.put("brightGreen", Colour.BRIGHT_GREEN);
        colours.put("brightGreen2", Colour.BLUE);
        colours.put("yellow", Colour.YELLOW);
        colours.put("pink", Colour.PINK);
        colours.put("turquoise", Colour.TURQUOISE);
        colours.put("darkRed", Colour.DARK_RED);
        colours.put("green", Colour.GREEN);
        colours.put("darkBlue", Colour.DARK_BLUE);
        colours.put("darkYellow", Colour.DARK_YELLOW);
        colours.put("violet", Colour.VIOLET);
        colours.put("teal", Colour.TEAL);
        colours.put("grey25", Colour.GREY_25_PERCENT);
        colours.put("grey50", Colour.GREY_50_PERCENT);
        colours.put("periwinkle", Colour.PERIWINKLE);
        colours.put("plum", Colour.PLUM);
        colours.put("ivory", Colour.IVORY);
        colours.put("lightTurquoise", Colour.LIGHT_TURQUOISE);
        colours.put("darkPurple", Colour.DARK_PURPLE);
        colours.put("coral", Colour.CORAL);
        colours.put("oceanBlue", Colour.OCEAN_BLUE);
        colours.put("iceBlue", Colour.ICE_BLUE);
        colours.put("darkBlue", Colour.DARK_BLUE);
        colours.put("skyBlue", Colour.SKY_BLUE);
        colours.put("paleBlue", Colour.PALE_BLUE);
        colours.put("gold", Colour.GOLD);

        return colours.get(ys);
    }
// "\ " 

    public static void main(String[] args) throws RowsExceededException, BiffException, WriteException, IOException {
        createWorkbook("D:/test111.xls", "sheetView", 0);
        openSheet("D:/test111.xls", "sheetView");
        setLinkToErrorScrenshot(1, 2, "D:/workplace/java/appautotest/failTestCaseScreen Shot/20151110-134533-812_test005login.png");
        close();
    }

}
