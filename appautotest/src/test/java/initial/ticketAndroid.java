package initial;

import java.util.Map;

import utils.ReadElementData;
import core.Initial;
import base.AndroidApp;;

public class ticketAndroid extends AndroidApp{
	String excelpath = "testresource\\test.xls";
	String elesheet = "票务系统";
	String sheetname = "票务系统测试用例";
	public String S_activityMore = "cn.beyondsoft.wicket.more.MoreActivity";
	public String S_appPackage = "cn.beyondsoft.wicket";
	public Map<String, Map<String, Map<String, String>>> elementData = getElementData(excelpath, elesheet);
	public Map<String, Object> testCaseData = getTestCaseData(excelpath, sheetname);
	public void runApp() throws Exception{
		apkName = "ticketingsystem.apk";
		appPackage = "cn.beyondsoft.wicket";
		mainActivity = "cn.beyondsoft.wicket.LoddingActivity";
		setUp();
	}
}
