package base;

import io.appium.java_client.android.AndroidElement;
import utils.CommonTools;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.UI;


public class AndroidApp extends UI{
	public static AndroidDriver<AndroidElement> driver;

		 
    public void runAndroidApp(){
        // set up appium
    	if (configFileName != null){
    		apkName = getProperties("apkName");
    		appPackage = getProperties("appPackage");
    		mainActivity = getProperties("mainActivity");
    	}


        File app = new File(appDir, apkName);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName",deviceName);
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("unicodeKeyboard", unicodeKeyboard);
        capabilities.setCapability("resetKeyboard", resetKeyboard);
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", mainActivity);
        try {
			driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        wait = new WebDriverWait(driver,waitTime);
        elementData = getElementData();

    	testCaseData = getTestCaseData();


        
    }

	
	
    public void getButtons(){
    	List<AndroidElement> eles= driver.findElementsByClassName("android.widget.Button");
    	if (eles.size()!=0){
    		System.out.println("The total of button is " + eles.size());

    		for (int i=0;i< eles.size();i++ ){
    			System.out.println("The "+i+" button is " +eles.get(i).getText());	    			
    		}	    			    			    		
    	}
		else {
				System.out.println("There is no textview ");
		}
	}
    
    public void getTextViews(){
    	List<AndroidElement> eles= driver.findElementsByClassName("android.widget.TextView");
    	if (eles.size()!=0){
    		System.out.println("The total of textview is " + eles.size());
    		int i=0;
    		for (AndroidElement ele : eles){
    			System.out.println("The "+i+" textview is " +ele.getText());
    			i++;
    			
    		}	    		    		
    	}
		else {
				System.out.println("There is no textview ");
		}
    }
    
    public void getEditTexts(){
    	List<AndroidElement> eles= driver.findElementsByClassName("android.widget.EditText");
    	if (eles.size()!=0){
    		System.out.println("The total of edittext is " + eles.size());
    		int i=0;
    		for (AndroidElement ele : eles){
    			System.out.println("The "+i+" edittext is " +ele.getText());
    			i++;
    			
    		}	    		    		
    	}
		else {
				System.out.println("There is no edittext ");
		}
    }
    
    public void getImageViews(){
    	List<AndroidElement> eles= driver.findElementsByClassName("android.widget.ImageView");
    	if (eles.size()!=0){
    		System.out.println("The total of imageview is " + eles.size());	    		    		
    	}
	   	else {
	   			System.out.println("There is no imageview ");
	   	}
    }
    
    
    
    public void getImageButtons(){
    	List<AndroidElement> eles= driver.findElementsByClassName("android.widget.ImageButton");
    	if (eles.size()!=0){
    		System.out.println("The total of imagebutton is " + eles.size());    		    		
    	}
	   	else {
	   			System.out.println("There is no imagebutton ");
   		}
    }
    
    protected AndroidElement findElementByClassNameIndex(String classname,int index){
    	List<AndroidElement> eles= driver.findElementsByClassName(classname);
    	return eles.get(index);
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
    
    public void clickEnter() throws IOException{
    	executeAdbShell("adb shell input keyevent 66");
    }
    
    public void clickBack() throws IOException{
    	executeAdbShell("adb shell input keyevent 4");
    }
    
    public void clickHome() throws IOException{
    	executeAdbShell("adb shell input keyevent 3");
    }
    
    public void clickMenu() throws IOException{
    	executeAdbShell("adb shell input keyevent 1");
    }
    

    
    public void executeAdbShell(String adbshell) throws IOException{
    	Runtime.getRuntime().exec(adbshell);
    }
    
    

	public void getScreen(String filename) throws IOException{ 
		File scrFile = driver.getScreenshotAs(OutputType.FILE);

	  	if (!(new File(dirName).isDirectory())) {  // 判断是否存在该目录
	  		new File(dirName).mkdir();  // 如果不存在则新建一个目录
	  	}
		FileUtils.copyFile(scrFile, new File(dirName+CommonTools.getCurrentTime()+ "_"+ filename+".jpg"));		
	}
	
	public void getScreen() throws IOException{ 
		getScreen("");
	}
	
	public AndroidElement findElement(String page,String name){

		String selecttype = elementData.get(page).get(name).get("SelectType");
		String location = elementData.get(page).get(name).get("Location");
		if (selecttype.equals("css")){
			return driver.findElement(By.cssSelector(location));
		}
		else if (selecttype.equals("id")){
			return driver.findElement(By.id(location));
		}
		else if (selecttype.equals("xpath")){
			return driver.findElement(By.xpath(location));
		}
		else if (selecttype.equals("name")){
			return driver.findElement(By.name(location));
		}

		else if (selecttype.equals("linktext")){
			return driver.findElement(By.linkText(location));
		}
		else if (selecttype.equals("partiallinktext")){
			return driver.findElement(By.partialLinkText(location));
		}
		else if (selecttype.equals("tagname")){
			return driver.findElement(By.tagName(location));
		}
		else if (selecttype.equals("scrollname")){
			return driver.scrollTo(location);
		}
		else if (selecttype.equals("index")){
			String[] sourceStrArray = location.split(",");
			String classname = sourceStrArray[0];
			String index = sourceStrArray[1];
			int in=Integer.parseInt(index);
			return findElementByClassNameIndex(classname, in);
		}		
		else{
			System.out.println("Can not find the element.");
		}
		return null;
	}
	
	public void tabElement(String page,String name){


		String selecttype = elementData.get(page).get(name).get("SelectType");
		String location = elementData.get(page).get(name).get("Location");
		if (selecttype.equals("tab")){
			String[] sourceStrArray = location.split(",");
			String x = sourceStrArray[0];
			String y = sourceStrArray[1];
			int newx=Integer.parseInt(x);
			int newy=Integer.parseInt(y);
			tab(newx, newy);
		}
		else{
			System.out.println("Please provide coordinate.");
		}
	}
	
	public void longTabElement(String page,String name){


		String selecttype = elementData.get(page).get(name).get("SelectType");
		String location = elementData.get(page).get(name).get("Location");
		if (selecttype.equals("tab")){
			String[] sourceStrArray = location.split(",");
			String x = sourceStrArray[0];
			String y = sourceStrArray[1];
			int newx=Integer.parseInt(x);
			int newy=Integer.parseInt(y);
			longTab(newx, newy);
		}
		else{
			System.out.println("Please provide coordinate.");
		}
	}
	public void swipeElement(String page,String name){


		String selecttype = elementData.get(page).get(name).get("SelectType");
		String location = elementData.get(page).get(name).get("Location");
		if (selecttype.equals("swipe")){
			String[] sourceStrArray = location.split(",");
			String startx = sourceStrArray[0];
			String starty = sourceStrArray[1];
			String endx = sourceStrArray[2];
			String endy = sourceStrArray[3];
			int startnewx=Integer.parseInt(startx);
			int startnewy=Integer.parseInt(starty);
			int endnewx=Integer.parseInt(endx);
			int endnewy=Integer.parseInt(endy);
			swipe(startnewx, startnewy, endnewx, endnewy, 1000);
		}
		else{
			System.out.println("Please provide coordinate.");
		}
	}
		
	   public void quit(){
	    	driver.quit();
	    }
	   
	   public void tab(int fingers, int x, int y, int duration ){

		   float a = x/basicWindowx;
		   float b = y/basicWindowy;
		   int newx = (int) (a*driver.manage().window().getSize().getWidth());
		   int newy = (int) (b*driver.manage().window().getSize().getHeight());		   
		   driver.tap(fingers, newx, newy, duration);
	   }
	   
	   public void tab(int x, int y){
		   tab(1, x, y, 500);
	   }
	   
	   public void longTab(int x, int y){
		   tab(1, x, y, 3000);
	   }
	   
	   public void swipe (int startx, int starty, int endx, int endy, int duration){
		   float a = startx/basicWindowx;
		   float b = starty/basicWindowy;
		   int newstartx = (int) (a*driver.manage().window().getSize().getWidth());
		   int newstarty = (int) (b*driver.manage().window().getSize().getHeight());	
		   float c = endx/basicWindowx;
		   float d = endy/basicWindowy;
		   int newendx = (int) (c*driver.manage().window().getSize().getWidth());
		   int newsendy = (int) (d*driver.manage().window().getSize().getHeight());
		   driver.swipe(newstartx, newstarty, newendx, newsendy, duration);
		   
	   }
	   
	   public void scrollTo(String text){
		   driver.scrollTo(text);
	   }
	   
	   public void scrollToClick(String text){
		   scrollTo(text);
		   driver.findElementByName(text).click();
	   }
	
	   
	   public void startActivity(String appPackage,String appActivity){
		   driver.startActivity(appPackage, appActivity);
	   }
	   
	   @SuppressWarnings({ "unchecked" })
	   public void runTestCase(String testCase){


			List<Map<String,String>> cases = (List<Map<String, String>>) testCaseData.get(testCase);
			String page = null;
			String name = null;	   
			
			for (int i = 0;i <cases.size();i++){

				String action = cases.get(i).get("Action");
				String element = cases.get(i).get("Element");
				String value = cases.get(i).get("Value");
				String actual = cases.get(i).get("Actual");
				String expected = cases.get(i).get("Expected");
				String row = cases.get(i).get("row");
				int rowin=Integer.parseInt(row);

				if (element !=""){
				String[] sourceStrArray = element.split("/");
				page = sourceStrArray[0];
				name = sourceStrArray[1];
				}

				if (action.equals("click")){
					clickElement(page, name);
					writeResult(rowin, 6, "P");

				}
				else if (action.equals("sleep")){
					int v=Integer.parseInt(value);
					CommonTools.sleep(v);
					writeResult(rowin, 6, "P");

				}
				else if (action.equals("waitDisplay")){
					waitDisplay(page, name);
					writeResult(rowin, 6, "P");

				}
				else if (action.equals("tap")){
					tabElement(page, name);
					writeResult(rowin, 6, "P");

				}
				else if (action.equals("longTap")){
					longTabElement(page, name);
					writeResult(rowin, 6, "P");

				}
				else if (action.equals("back")){
					try {
						clickBack();
						writeResult(rowin, 6, "P");

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if (action.equals("enter")){
					try {
						clickEnter();
						writeResult(rowin, 6, "P");

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
				}
				else if (action.equals("home")){
					try {
						clickHome();
						writeResult(rowin, 6, "P");

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if (action.equals("menu")){
					try {
						clickMenu();
						writeResult(rowin, 6, "P");

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if (action.equals("swipeOfType")){
					swipeOfType(value);
					writeResult(rowin, 6, "P");

				}
				else if (action.equals("sendKey")){
					sendKeys(page, name, value);
					writeResult(rowin, 6, "P");

				}
				else if (action.equals("assert")){
					actual = getElementText(page, name);
					assertEquals(actual, expected);
					writeResult(rowin, 6, "P");

					
				}
				else if (action.equals("runTestCase")){
					runTestCase(value);
					writeResult(rowin, 6, "P");

				}
				else if (action.equals("startActivity")){
					startActivity(appPackage, value);
					writeResult(rowin, 6, "P");


				}
				else if (action.equals("swipe")){
					swipeElement(page, name);


				}
				else if (action.equals("scrollToClick")){
					scrollToClick(value);


				}

				else{
					CommonTools.log("Can not run the action");

				}

			}
	   }

}
