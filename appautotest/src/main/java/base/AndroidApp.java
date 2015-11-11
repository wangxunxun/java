package base;

import io.appium.java_client.android.AndroidElement;
import utils.CommonTools;
import utils.OperateExcel;
import utils.TestngListener;
import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import core.UI;

public class AndroidApp extends UI {
	public static AndroidDriver<AndroidElement> driver;

	public void runAndroidApp() {
		// set up appium
		try {
			initialAndroidData();
		} catch (Exception e) {
			System.err.println(e.toString());
		}

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", androidDeviceName);
		capabilities.setCapability("app", appDir + apkName);
		capabilities.setCapability("unicodeKeyboard", unicodeKeyboard);
		capabilities.setCapability("resetKeyboard", resetKeyboard);
		capabilities.setCapability("appPackage", appPackage);
		capabilities.setCapability("appActivity", mainActivity);
		try {
			driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait = new WebDriverWait(driver, waitTime);

	}


	public void quit() {
		try {
			OperateExcel testSummaySheet = new OperateExcel(testReportDir + testReportName + ".xls", testSummarySheetName);
			testSummaySheet.setFormat(10, true);
			testSummaySheet.writeTestSummaryToExcel(TestngListener.classData);
			testSummaySheet.close();
			TestngListener.classData.clear();
			
			
			OperateExcel testClassSheet = new OperateExcel(testReportDir + testReportName + ".xls", testClassName);
			testClassSheet.setColumnView(1, 100);
			testClassSheet.setColumnView(0, 40);
			testClassSheet.setFormat(10, true);
			testClassSheet.setHyperLinkForSheet(0, 0, "Back", testSummarySheetName, 0, 0);
			testClassSheet.writeLogToExcel(logData);
			testClassSheet.close();
			logData.clear();
			
			String excelPath = CommonTools.setPath(testDataExcelPath);
			if(writeResult==true){
				CommonTools.writeResultToExcel(excelPath, testCaseSheet, testResultData);
				testResultData.clear();
			}
			if(writeScript == true){
				CommonTools.writeScriptToExcel(excelPath, testCaseSheet, testScriptData);
				testScriptData.clear();
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		driver.quit();
	}
	public String getApkName() {
		return apkName;
	}

	public String getAppPackage() {
		return appPackage;
	}

	public void getButtons() {
		List<AndroidElement> eles = driver.findElementsByClassName("android.widget.Button");
		if (eles.size() != 0) {
			log("The total of button is " + eles.size());

			for (int i = 0; i < eles.size(); i++) {
				log("The " + i + " button is " + eles.get(i).getText());
			}
		} else {
			log("There is no textview ");
		}
	}

	public void getTextViews() {
		List<AndroidElement> eles = driver.findElementsByClassName("android.widget.TextView");
		if (eles.size() != 0) {
			log("The total of textview is " + eles.size());
			int i = 0;
			for (AndroidElement ele : eles) {
				log("The " + i + " textview is " + ele.getText());
				i++;

			}
		} else {
			log("There is no textview ");
		}
	}

	public void getEditTexts() {
		List<AndroidElement> eles = driver.findElementsByClassName("android.widget.EditText");
		if (eles.size() != 0) {
			log("The total of edittext is " + eles.size());
			int i = 0;
			for (AndroidElement ele : eles) {
				log("The " + i + " edittext is " + ele.getText());
				i++;

			}
		} else {
			log("There is no edittext ");
		}
	}

	public void getImageViews() {
		List<AndroidElement> eles = driver.findElementsByClassName("android.widget.ImageView");
		if (eles.size() != 0) {
			log("The total of imageview is " + eles.size());
		} else {
			log("There is no imageview ");
		}
	}

	public void getImageButtons() {
		List<AndroidElement> eles = driver.findElementsByClassName("android.widget.ImageButton");
		if (eles.size() != 0) {
			log("The total of imagebutton is " + eles.size());
		} else {
			log("There is no imagebutton ");
		}
	}

	public AndroidElement findElementByClassNameIndex(String classname, int index) {
		List<AndroidElement> eles = driver.findElementsByClassName(classname);
		return eles.get(index);
	}

	public void swipeOfType(String type) {
		int windowlenX = driver.manage().window().getSize().getWidth();
		int windowlenY = driver.manage().window().getSize().getHeight();
		String swipeLeft = "left";

		String swipeRight = "right";

		String swipeUp = "up";

		String swipeDown = "down";

		// Sliding screen to the left
		if (type.equalsIgnoreCase(swipeLeft)) {
			log("Swipe left.");
			driver.swipe((int) (windowlenX * 0.9), (int) (windowlenY * 0.5), (int) (windowlenX * 0.1),
					(int) (windowlenY * 0.5), 3000);
		}

		// Sliding screen to the right
		if (type.equalsIgnoreCase(swipeRight)) {
			log("Swipe right.");
			driver.swipe((int) (windowlenX * 0.1), (int) (windowlenY * 0.5), (int) (windowlenX * 0.9),
					(int) (windowlenY * 0.5), 3000);
		}

		// Screen upward sliding
		if (type.equalsIgnoreCase(swipeUp)) {
			log("Swipe up.");
			driver.swipe((int) (windowlenX * 0.5), (int) (windowlenY * 0.8), (int) (windowlenX * 0.5),
					(int) (windowlenY * 0.4), 3000);
		}

		// Slide down the screen
		if (type.equalsIgnoreCase(swipeDown)) {
			log("Swipe down.");
			driver.swipe((int) (windowlenX * 0.5), (int) (windowlenY * 0.4), (int) (windowlenX * 0.5),
					(int) (windowlenY * 0.8), 3000);
		}

	}

	public void clickEnter() {
		log("Click Enter.");
		executeAdbShell("adb shell input keyevent 66");
	}

	public void clickBack() {
		log("Click back.");
		executeAdbShell("adb shell input keyevent 4");
	}

	public void clickHome() {
		log("Click Home.");
		executeAdbShell("adb shell input keyevent 3");
	}

	public void clickMenu() {
		log("Click Menu.");
		executeAdbShell("adb shell input keyevent 1");
	}

	public void executeAdbShell(String adbshell) {
		try {
			log("Execute adb Shell \"" + adbshell + "\".");
			Runtime.getRuntime().exec(adbshell);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public AndroidElement findElement(String page, String name) {

		String selecttype = elementData.get(page).get(name).get("SelectType");
		String location = elementData.get(page).get(name).get("Location");
		if (selecttype.equals("css")) {
			return driver.findElement(By.cssSelector(location));
		} else if (selecttype.equals("id")) {
			return driver.findElement(By.id(location));
		} else if (selecttype.equals("xpath")) {
			return driver.findElement(By.xpath(location));
		} else if (selecttype.equals("name")) {
			return driver.findElement(By.name(location));
		}

		else if (selecttype.equals("linktext")) {
			return driver.findElement(By.linkText(location));
		} else if (selecttype.equals("partiallinktext")) {
			return driver.findElement(By.partialLinkText(location));
		} else if (selecttype.equals("tagname")) {
			return driver.findElement(By.tagName(location));
		} else if (selecttype.equals("scrollname")) {
			return driver.scrollTo(location);
		} else if (selecttype.equals("index")) {
			String[] sourceStrArray = location.split(",");
			String classname = sourceStrArray[0];
			String index = sourceStrArray[1];
			int in = Integer.parseInt(index);
			return findElementByClassNameIndex(classname, in);
		} else {
			log("Can not find the element.");
		}
		return null;
	}

	public void tabElement(String page, String name) {

		String selecttype = elementData.get(page).get(name).get("SelectType");
		String location = elementData.get(page).get(name).get("Location");
		if (selecttype.equals("tab")) {
			String[] sourceStrArray = location.split(",");
			String x = sourceStrArray[0];
			String y = sourceStrArray[1];
			int newx = Integer.parseInt(x);
			int newy = Integer.parseInt(y);
			tab(newx, newy);
		} else {
			log("Please provide the coordinate.");
		}
	}

	public void longTabElement(String page, String name) {

		String selecttype = elementData.get(page).get(name).get("SelectType");
		String location = elementData.get(page).get(name).get("Location");
		if (selecttype.equals("tab")) {
			String[] sourceStrArray = location.split(",");
			String x = sourceStrArray[0];
			String y = sourceStrArray[1];
			int newx = Integer.parseInt(x);
			int newy = Integer.parseInt(y);
			longTab(newx, newy);
		} else {
			log("Please provide the coordinate.");
		}
	}

	public void swipeElement(String page, String name) {

		String selecttype = elementData.get(page).get(name).get("SelectType");
		String location = elementData.get(page).get(name).get("Location");
		if (selecttype.equals("swipe")) {
			String[] sourceStrArray = location.split(",");
			String startx = sourceStrArray[0];
			String starty = sourceStrArray[1];
			String endx = sourceStrArray[2];
			String endy = sourceStrArray[3];
			int startnewx = Integer.parseInt(startx);
			int startnewy = Integer.parseInt(starty);
			int endnewx = Integer.parseInt(endx);
			int endnewy = Integer.parseInt(endy);
			swipe(startnewx, startnewy, endnewx, endnewy, 1000);
		} else {
			log("Please provide the coordinate.");
		}
	}


	public void tab(int fingers, int x, int y, int duration) {
		float a = (float) x / basicWindowX;
		float b = (float) y / basicWindowY;
		int newx = (int) (a * driver.manage().window().getSize().getWidth());
		int newy = (int) (b * driver.manage().window().getSize().getHeight());
		driver.tap(fingers, newx, newy, duration);
	}

	public void tab(int x, int y) {
		log("Tab (" + x + "," + y + ").");
		tab(1, x, y, 500);
	}

	public void longTab(int x, int y) {
		log("Long tab (" + x + "," + y + ").");
		tab(1, x, y, 3000);
	}

	public void clickElementByXY(String page, String name) {
		log("Click the " + name + " element on the " + page + " page by XY.");
		int X = getElementLocateX(page, name);
		int Y = getElementLocateY(page, name);
		tab(X + 10, Y + 10);
	}

	public void swipe(int startx, int starty, int endx, int endy, int duration) {
		float a = startx / basicWindowX;
		float b = starty / basicWindowY;
		int newstartx = (int) (a * driver.manage().window().getSize().getWidth());
		int newstarty = (int) (b * driver.manage().window().getSize().getHeight());
		float c = endx / basicWindowX;
		float d = endy / basicWindowY;
		int newendx = (int) (c * driver.manage().window().getSize().getWidth());
		int newsendy = (int) (d * driver.manage().window().getSize().getHeight());
		log("Swipe" + "(" + startx + "," + starty + "," + endx + "," + endy + ").");
		driver.swipe(newstartx, newstarty, newendx, newsendy, duration);

	}

	public void scrollTo(String text) {
		log("Scroll to " + text + ".");
		driver.scrollTo(text);
	}

	public void scrollToClick(String text) {
		scrollTo(text);
		log("Click " + text + ".");
		driver.findElementByName(text).click();
	}

	public void startActivity(String appPackage, String appActivity) {
		log("Start to launch activity " + appActivity + ".");
		driver.startActivity(appPackage, appActivity);
	}

	public void slideUpToFindElement(String page, String name) {
		boolean statu = false;

		for (int i = 1; i < 5; i++) {
			statu = verifyDisplay(page, name);
			if (statu == true) {
				break;
			} else {
				log("Start to swipe up " + i + " times.");
				swipeOfType("up");
			}
		}
		if (statu == false) {
			log("Don't find the " + name + " element on the " + page + " page.");
			Assert.fail("Don't find the " + name + " element on the " + page + " page.");
		}
	}

	@SuppressWarnings({ "unchecked" })
	public void runTestCase(String testCase) {

		List<Map<String, String>> cases = (List<Map<String, String>>) testCaseData.get(testCase);

		for (int i = 0; i < cases.size(); i++) {

			String action = cases.get(i).get("Action");
			String page = cases.get(i).get("Page");
			String name = cases.get(i).get("Element");
			String value = cases.get(i).get("Value");
			String actual = cases.get(i).get("Actual");
			String expected = cases.get(i).get("Expected");
			String row = cases.get(i).get("row");
			int rowin = Integer.parseInt(row);

			if (action.equals("click")) {
				clickElement(page, name);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "clickElement(\"" + page + "\",\"" + name + "\");";
				putScriptData(rowin, script);

			} else if (action.equals("sleep")) {
				int v = Integer.parseInt(value);

				sleep(v);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "sleep(" + v + ");";
				putScriptData(rowin, script);

			} else if (action.equals("waitDisplay")) {
				waitDisplay(page, name);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "waitDisplay(\"" + page + "\",\"" + name + "\");";
				putScriptData(rowin, script);

			} else if (action.equals("tap")) {
				tabElement(page, name);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "tap(\"" + page + "\",\"" + name + "\");";
				putScriptData(rowin, script);

			} else if (action.equals("longTap")) {
				longTabElement(page, name);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "longTap(\"" + page + "\",\"" + name + "\");";
				putScriptData(rowin, script);

			} else if (action.equals("clear")) {
				clear(page, name);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "clear(\"" + page + "\",\"" + name + "\");";
				putScriptData(rowin, script);
			} else if (action.equals("back")) {
				clickBack();
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "clickBack();";
				putScriptData(rowin, script);
			} else if (action.equals("enter")) {
				clickEnter();
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "clickEnter();";
				putScriptData(rowin, script);
			} else if (action.equals("home")) {
				clickHome();
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "clickHome();";
				putScriptData(rowin, script);
			} else if (action.equals("menu")) {
				clickMenu();
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "clickMenu();";
				putScriptData(rowin, script);
			} else if (action.equals("swipeOfType")) {
				swipeOfType(value);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "swipeOfType(\"" + value + "\");";
				putScriptData(rowin, script);

			} else if (action.equals("sendKey")) {
				sendKeys(page, name, value);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "sendKeys(\"" + page + "\",\"" + name + "\",\"" + value + "\");";
				putScriptData(rowin, script);

			} else if (action.equals("assert")) {
				actual = getElementText(page, name);
				assertEquals(actual, expected);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "assertEquals(" + appClass + "." + "getElementText(\"" + page + "\",\""
						+ name + "\")" + "," + "\"" + expected + "\");";
				putScriptData(rowin, script);

			} else if (action.equals("runTestCase")) {
				log("Run the " + value + " test case.");
				runTestCase(value);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "runTestCase(\"" + value + "\");";
				putScriptData(rowin, script);

			} else if (action.equals("startActivity")) {
				startActivity(appPackage, value);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "startActivity(\"" + appPackage + "\"" + ",\"" + value + "\");";
				putScriptData(rowin, script);

			} else if (action.equals("swipe")) {
				swipeElement(page, name);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "swipeElement(\"" + page + "\",\"" + name + "\");";
				putScriptData(rowin, script);

			} else if (action.equals("scrollToClick")) {
				scrollToClick(value);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "scrollToClick(\"" + value + "\");";
				putScriptData(rowin, script);

			} else if (action.equals("slideToElement")) {
				slideUpToFindElement(page, name);
				logResult(rowin);
				putResultData(rowin, "P");
				String script = appClass + "." + "slideUPToFindElement(\"" + page + "\",\"" + name + "\");";
				putScriptData(rowin, script);

			}

			else {
				log("Can not run the action");

			}

		}
	}

}
