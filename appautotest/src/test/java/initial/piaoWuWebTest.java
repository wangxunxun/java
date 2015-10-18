package initial;
import base.WebApp;

public class piaoWuWebTest extends WebApp{

	public void initialTestData(){
		configFileName = "/config/ticketSystem/configWeb.properties";
//		testExcelPath = getProperties("testExcelPath");
//		elementSheet = getProperties("elementSheet");

	}
	
	public void enterHomePage(){
		String url=getProperties("url");
		get(url);
		sendKeys("登录页", "登录输入框", "admin");
		sendKeys("登录页", "密码输入框", "admin");
		clickElement("登录页", "登录");
	}
}
