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
    	NPAndroid.sleep(20000);
    	NPAndroid.swipeOfType("up");
    	NPAndroid.swipeOfType("up");
    	NPAndroid.swipeOfType("up");
    	NPAndroid.sleep(5000);
    	NPAndroid.waitDisplay("首页","广告");
    	NPAndroid.sleep(5000);
    	int x = NPAndroid.getElementX("首页","广告");
    	int y = NPAndroid.getElementY("首页","广告");
    	NPAndroid.log(x);
    	NPAndroid.log(y);


    	



    	NPAndroid.sleep(5000);


//    	NPAndroid.runTestCase("进入首页");



    }
}
