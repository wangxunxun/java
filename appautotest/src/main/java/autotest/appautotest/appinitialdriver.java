package autotest.appautotest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class appinitialdriver {
	 public AppiumDriver driver;
	 public WebDriverWait wait; 
	 
	    @BeforeTest
	    public void setUp() throws Exception {
	        // set up appium
	        File classpathRoot = new File(System.getProperty("user.dir"));
	        File appDir = new File(classpathRoot, "/testresource/apps");
	        File app = new File(appDir, "gaodedaohang_1105.apk");
	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
	        capabilities.setCapability("platformName", "Android");
	        capabilities.setCapability("deviceName","XiaoMi2");
	        capabilities.setCapability("platformVersion", "4.4");
	        capabilities.setCapability("app", app.getAbsolutePath());
	        capabilities.setCapability("appPackage", "com.autonavi.xmgd.navigator");
	        capabilities.setCapability("appActivity", "com.autonavi.xmgd.navigator.Warn");
	        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	        wait = new WebDriverWait(driver,10);
	        
//	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        
	    }
	 
	    @AfterTest
	    public void tearDown() throws Exception {
	        driver.quit();
	    }
	    
	    public void getbuttons(){
	    	List<AndroidElement> eles= driver.findElementsByClassName("android.widget.Button");
	    	if (eles.size()!=0){
	    		System.out.println("The total of button is " + eles.size());

	    		for (int i=0;i< eles.size();i++ ){
	    			System.out.println("The "+(i+1)+" button is " +eles.get(i).getText());	    			
	    		}	    			    			    		
	    	}
   	else {
   			System.out.println("There is no textview ");
   		}
	    }
	    
	    public void gettextviews(){
	    	List<AndroidElement> eles= driver.findElementsByClassName("android.widget.TextView");
	    	if (eles.size()!=0){
	    		System.out.println("The total of textviews is " + eles.size());
	    		int i=1;
	    		for (AndroidElement ele : eles){
	    			System.out.println("The "+i+" textview is " +ele.getText());
	    			i++;
	    			
	    		}	    		    		
	    	}
   	else {
   			System.out.println("There is no textview ");
   		}
	    }
	    
	    public void getedittexts(){
	    	List<AndroidElement> eles= driver.findElementsByClassName("android.widget.EditText");
	    	if (eles.size()!=0){
	    		System.out.println("The total of edittexts is " + eles.size());
	    		int i=1;
	    		for (AndroidElement ele : eles){
	    			System.out.println("The "+i+" edittext is " +ele.getText());
	    			i++;
	    			
	    		}	    		    		
	    	}
   	else {
   			System.out.println("There is no edittext ");
   		}
	    }
	    
	    public void getimageviews(){
	    	List<AndroidElement> eles= driver.findElementsByClassName("android.widget.ImageView");
	    	if (eles.size()!=0){
	    		System.out.println("The total of imageviews is " + eles.size());
	    		int i=1;
	    		for (AndroidElement ele : eles){
	    			System.out.println("The "+i+" imageview is " +ele.getText());
	    			i++;
	    			
	    		}	    		    		
	    	}
   	else {
   			System.out.println("There is no imageview ");
   		}
	    }
	    
	    
	    
	    public void getimagebuttons(){
	    	List<AndroidElement> eles= driver.findElementsByClassName("android.widget.ImageButton");
	    	if (eles.size()!=0){
	    		System.out.println("The total of imagebuttons is " + eles.size());
	    		int i=1;
	    		for (AndroidElement ele : eles){
	    			System.out.println("The "+i+" imagebutton is " +ele.getText());
	    			i++;
	    			
	    		}	    		    		
	    	}
   	else {
   			System.out.println("There is no imagebutton ");
   		}
	    }
	    
	    public AndroidElement returnelebyclassname(String classname,int index){
	    	List<AndroidElement> eles= driver.findElementsByClassName(classname);
	    	return eles.get(index-1);
	    }

}
