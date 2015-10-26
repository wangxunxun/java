package NPAndroid;

import java.util.List;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import base.AndroidApp;
import initial.npAndroid;
import io.appium.java_client.android.AndroidElement;
import utils.CommonTools;


public class ad {
	npAndroid NPAndroid = new npAndroid();
	@BeforeClass
	public void setUp(){
		NPAndroid.initialTestData();
		
		NPAndroid.runAndroidApp();
		NPAndroid.setTestClassName(this.getClass().getName());
		NPAndroid.log("Start to run "+this.getClass().getName()+".");
	}

    @AfterClass
    public void tearDown(){

    	NPAndroid.quit();
    }
    
    @Test
    public void login(){
    	NPAndroid.sleep(5000);
    	NPAndroid.waitDisplay("启动页", "略过");
    	NPAndroid.clickElement("启动页", "略过");
    	NPAndroid.sleep(15000);
    	NPAndroid.swipeOfType("up");
    	NPAndroid.swipeOfType("up");
    	NPAndroid.sleep(5000);
    	NPAndroid.getImageViews();
    	NPAndroid.log(NPAndroid.findElement("首页","广告").getSize().getHeight());
    	AndroidElement eee = NPAndroid.findElementByClassNameIndex("android.widget.ImageView", 0);
    	NPAndroid.log(eee.getSize().getHeight());
    	List<AndroidElement> ele = AndroidApp.driver.findElementsByClassName("android.widget.ImageView");
    	NPAndroid.log(ele.get(0).getSize().getHeight());
    	List<AndroidElement> ele1 = AndroidApp.driver.findElementsByClassName("android.webkit.WebView");
    	NPAndroid.log(ele1.get(0).getSize().getHeight());
    	NPAndroid.log(ele1.get(0).getSize().getWidth());
    	NPAndroid.log(ele1.get(0).getLocation().getX());
    	NPAndroid.log(ele1.get(0).getLocation().getY());
    	ele1.get(0).click();
    	

    	int x = NPAndroid.getElementX("首页","广告");
    	NPAndroid.sleep(5000);
    	NPAndroid.log(x);

    	NPAndroid.sleep(5000);


//    	NPAndroid.runTestCase("进入首页");



    }
}
