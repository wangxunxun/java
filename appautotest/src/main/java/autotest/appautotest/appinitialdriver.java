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
	    
	    public void swipeOfType(String type) {

	        int windowlenX = driver.manage().window().getSize().getWidth();
	        int windowlenY = driver.manage().window().getSize().getHeight();
	        String swipeLeft = "left";
	        String swipeLeftSide = "leftSide";
	        String swipeRight = "right";
	        String swipeRightSide = "rightSide";
	        String swipeUp = "up";
	        String swipeTop = "top";
	        String swipeDown = "down";
	        String swipeBottom = "bottom";

	        // Sliding screen to the left
	        if (type.equalsIgnoreCase(swipeLeft)) {
	            driver.swipe((int) (windowlenX * 0.9), (int) (windowlenY * 0.5), (int) (windowlenX * 0.2), (int) (windowlenY * 0.5),
	                    3000);
	        }

	        // From the left of screen to began to slip
	        if (type.equalsIgnoreCase(swipeLeftSide)) {

	            driver.swipe(1, (int) (windowlenY * 0.5), (int) (windowlenX * 0.9), (int) (windowlenY * 0.5), 3000);
	        }

	        // Sliding screen to the right
	        if (type.equalsIgnoreCase(swipeRight)) {

	            driver.swipe((int) (windowlenX * 0.2), (int) (windowlenY * 0.5), (int) (windowlenX * 0.9), (int) (windowlenY * 0.5),
	                    3000);
	        }

	        // From the right of screen to began to slip
	        if (type.equalsIgnoreCase(swipeRightSide)) {

	            driver.swipe((int) (windowlenX * 0.9), (int) (windowlenY * 0.5), (int) (windowlenX * 0.2), (int) (windowlenY * 0.5),
	                    3000);
	        }

	        // Screen upward sliding
	        if (type.equalsIgnoreCase(swipeUp)) {

	            driver.swipe((int) (windowlenX * 0.5), (int) (windowlenY * 0.9), (int) (windowlenX * 0.5), (int) (windowlenY * 0.4),
	                    3000);
	        }

	        // From the top of screen to began to slip
	        if (type.equalsIgnoreCase(swipeTop)) {

	            driver.swipe((int) (windowlenX * 0.5), 0, (int) (windowlenX * 0.5), (int) (windowlenY * 0.8), 3000);
	        }

	        // Slide down the screen
	        if (type.equalsIgnoreCase(swipeDown)) {

	            driver.swipe((int) (windowlenX * 0.5), (int) (windowlenY * 0.4), (int) (windowlenX * 0.5), (int) (windowlenY * 0.9),
	                    3000);
	        }

	        // From the bottom of screen to began to slip
	        if (type.equalsIgnoreCase(swipeBottom)) {

	            driver.swipe((int) (windowlenX * 0.5), (int)(windowlenY * 0.9), (int) (windowlenX * 0.5), (int) (windowlenY * 0.1), 3000);
	        }

	    }

	    protected void log(String content, Integer type) {

	        switch (type) {
	        case 1: {
	            System.out.println(CommonTools.getCurrentTime() + " INFO - " + content);
	            break;
	        }
	        case 2: {
	            System.err.println(CommonTools.getCurrentTime() + " ERROR - " + content);
	            break;
	        }
	        case 3: {
	            System.out.println(CommonTools.getCurrentTime() + " WARNING - " + content);
	            break;
	        }
	        case 4: {
	            System.err.println(CommonTools.getCurrentTime() + " WARNING - " + content);
	            break;
	        }
	       }

	    }

	    public void log(String content) {

	        log(content, 1);
	    }
	    
	    public void waitByTimeOut(int millis) {
	        try {
	            Thread.sleep(millis);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	    
}
