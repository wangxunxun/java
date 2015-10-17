package initial;


import base.AndroidApp;
import utils.CommonTools;;

public class ticketAndroid extends AndroidApp{

	

	public void initialTestData(){
		configFileName = "/config/ticketSystem/configAndroid.properties";
		apkName = getProperties("apkName");
		appPackage = getProperties("appPackage");
		mainActivity = getProperties("mainActivity");
		testExcelPath = getProperties("testExcelPath");
		elementSheet = getProperties("elementSheet");
		testCaseSheet = getProperties("testCaseSheet");



	}

}
