package base;

import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;
import utils.CommonTools;
import core.Initial;

public class IOSApp extends Initial {
	public static IOSDriver driver;
	public WebDriverWait wait; 
	static CommonTools tool =new CommonTools();
}
