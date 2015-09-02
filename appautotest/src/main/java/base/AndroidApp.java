package base;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidDriver;







import java.io.File;
import java.io.IOException;
import java.util.List;



import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;



import core.UI;


public class AndroidApp extends UI{
	public static AndroidDriver<AndroidElement> driver;


	
	
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
    
    public AndroidElement findElementByClassNameIndex(String classname,int index){
    	List<AndroidElement> eles= driver.findElementsByClassName("android.widget."+classname);
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
    public void sendKeysByElement(String content,AndroidElement ele){
    	int i=1;	    	
    	while (i<3){
    		ele.clear();
    		i++;
    	}
    	ele.sendKeys(content);		    	    	
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
    
    

	public static void getScreen(String filename) throws IOException{ 
		File scrFile = driver.getScreenshotAs(OutputType.FILE);
		String dir_name = tool.setCurrentPath("\\screenshot\\");
	  	if (!(new File(dir_name).isDirectory())) {  // 判断是否存在该目录
	  		new File(dir_name).mkdir();  // 如果不存在则新建一个目录
	  	}
		FileUtils.copyFile(scrFile, new File(dir_name+tool.getCurrentTime()+ "_"+ filename+".jpg"));		
	}
	
	public static void getScreen() throws IOException{ 
		getScreen("");
	}
	
	public AndroidElement findElement(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
		String selecttype = eledata.get(page).get(name).get("SelectType");
		String location = eledata.get(page).get(name).get("Location");
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
		else if (selecttype.equals("scrollname")){
			return driver.scrollTo(location);
		}
		else if (selecttype.equals("linktext")){
			return driver.findElement(By.linkText(location));

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
	
	public void tapElement(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
		String selecttype = eledata.get(page).get(name).get("SelectType");
		String location = eledata.get(page).get(name).get("Location");
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
	
	public void longTapElement(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
		String selecttype = eledata.get(page).get(name).get("SelectType");
		String location = eledata.get(page).get(name).get("Location");
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
	public void swipeElement(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
		String selecttype = eledata.get(page).get(name).get("SelectType");
		String location = eledata.get(page).get(name).get("Location");
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

		   float a = x/basicwindowx;
		   float b = y/basicwindowy;
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
		   float a = startx/basicwindowx;
		   float b = starty/basicwindowy;
		   int newstartx = (int) (a*driver.manage().window().getSize().getWidth());
		   int newstarty = (int) (b*driver.manage().window().getSize().getHeight());	
		   float c = endx/basicwindowx;
		   float d = endy/basicwindowy;
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
	
}
