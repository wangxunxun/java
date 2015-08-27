package base;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.Initial;
public class WebApp extends Initial{
	public static ChromeDriver driver;	
	public WebDriverWait wait; 
	
	
	public WebElement findelement(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
		String selecttype = eledata.get(page).get(name).get("SelectType");
		String location = eledata.get(page).get(name).get("Location");
		if (selecttype.equals("css")){
			return driver.findElementByCssSelector(location);
		}
		if (selecttype.equals("id")){
			return driver.findElementById(location);
		}
		if (selecttype.equals("xpath")){
			return driver.findElementByXPath(location);
		}
		else{
			System.out.println("Can not find the element.");
		}
		return null;
	}
}
