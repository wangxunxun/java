package initial;
import base.WebApp;

public class piaoWuWebTest extends WebApp{

	public void initialTestData(){

		testExcelPath = "testresource/项目/票务Web.xls";
		elementSheet = "票务系统Web";
		testCaseSheet = "票务系统测试用例";
		testDataSheet = null;

	}
	
	public void enterHomePage(String url){
		get(url);
		sendKeys("登录页", "登录输入框", "admin");
		sendKeys("登录页", "密码输入框", "admin");
		clickElement("登录页", "登录");
	}
}
