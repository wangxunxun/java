package initial;
import base.WebApp;

public class piaoWuWebTest extends WebApp{

	public void initialTestData(){
		configFileName = "/config/ticketSystem/configWeb.properties";
		testCaseClassName = this.getClass().getName();

	}
	
	public void enterHomePage(){
		String url=getProperties("url");
//		String url = "http://106.185.47.124:5000/Server/Auth/Login?next=%2F";
		get(url);
		sendKeys("登录页", "登录输入框", "admin");
		sendKeys("登录页", "密码输入框", "admin");
		clickElement("登录页", "登录");
	}
}
