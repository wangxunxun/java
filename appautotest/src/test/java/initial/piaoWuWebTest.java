package initial;
import base.WebApp;

public class piaoWuWebTest extends WebApp{

	public void initialTestData(){
		configFileName = "/config/ticketSystem/configWeb.properties";
	}
	
	public void enterHomePage(){
		String url=getProperties("url");
		get(url);
		waitDisplay("登录页", "登录输入框");
		sendKeys("登录页", "登录输入框", "admin");
		waitDisplay("登录页", "密码输入框");
		sendKeys("登录页", "密码输入框", "admin");
		clickElement("登录页", "登录");
	}
}
