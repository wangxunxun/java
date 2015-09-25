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
	public Map<String, Map<String, Map<String, String>>> eledata = initialeledata(excelpath, elesheet);
	public Map<String, Object> testData = initialTestCaseData(excelpath, sheetname);
	public void runApp() throws Exception{
		setUp("ticketingsystem.apk","cn.beyondsoft.wicket","cn.beyondsoft.wicket.LoddingActivity");
	}
}
