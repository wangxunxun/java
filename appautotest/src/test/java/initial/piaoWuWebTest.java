package initial;
import base.WebApp;

public class piaoWuWebTest extends WebApp{
	public String url="http://106.185.47.124:5000/Server/Auth/Login?next=%2F";
	public void initialTestData(){

		testExcelPath = "testresource/项目/票务Web.xls";
		elementSheet = "票务系统Web";
		testCaseSheet = "票务系统测试用例";
		testDataSheet = null;

	}
	
	public void enterHomePage(){
		get(url);
		sendKeys("登录页", "登录输入框", "admin");
		sendKeys("登录页", "密码输入框", "admin");
		clickElement("登录页", "登录");
	}
}
