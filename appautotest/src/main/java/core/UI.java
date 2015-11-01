package core;



import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import core.Initial;
import utils.CommonTools;
import utils.ImageUtils;
public class UI extends Initial{
	

    
    public void clickElement(String page,String name){
    	log("Click the "+name+" element on the "+page+" page.");
    	findElement(page, name).click();
    	
    }
    

    public void waitToclickElement(String page,String name){
    	waitDisplay(page, name);
    	log("Click the "+name+" element on the "+page+" page.");
    	findElement(page, name).click();
    	
    }
    
    public void waitToSendKeys(String page,String name,String value){
    	waitDisplay(page, name);
    	log("Send the "+value+" to the "+name+" element on the "+page+" page.");
    	findElement(page, name).sendKeys(value);
    }
    public void sendKeys(String page,String name,String value){
    	log("Send the \""+value+"\" value to the "+name+" element on the "+page+" page.");
    	findElement(page, name).sendKeys(value);
    }
    public void waitToClear(String page,String name){
    	waitDisplay(page, name);
    	log("Clear the "+name+" element on the "+page+" page.");
    	findElement(page, name).clear();
    }
    public void clear(String page,String name){
    	log("Clear the "+name+" element on the "+page+" page.");
    	findElement(page, name).clear();
    }
    
    public String getElementText(String page,String name){
    	log("To get the text of the "+name+" element on the "+page+" page.");
    	String value = findElement(page, name).getText();
    	if(value !=null){
    		log("The text is \""+value+"\".");
    		return value;
    	}
    	else{
    		log("The text is null.");
    	}
    	return null;
    }
    
    protected void executeJavaScript(String js, WebElement element) {
        ((JavascriptExecutor) driver).executeScript(js, element);
    }
    
    public void assertEquals(String actual, String expected){
    	Assert.assertEquals(actual, expected);
    }
    

    
    public boolean isSlected(String page,String name){
    	return findElement(page, name).isSelected();
    }
    
    public boolean isDisplayed(String page,String name){
    	return findElement(page, name).isDisplayed();
    }
    
    public boolean isEnabled(String page,String name){
    	return findElement(page, name).isEnabled();
    }
    
	public void sleep(int time){
		log("Sleep "+time+" ms.");
		CommonTools.sleep(time);
	}


	public WebElement findElement(String page,String name){

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
		else{
			System.out.println("Can not find the element.");
		}
		return null;
	}
	public void waitDisplay(String page,String name){
		waitDisplay(page, name, 2);
	}
	
	
	public void waitDisplay(String page,String name,int count){
		boolean statu = false;
		for(int i = 0;i<count;i++){
			statu = verifyDisplay(page, name);
			if(statu==false){
				if(i<count-1){
					log("Start to find the "+name+" element on the "+page+" page again.");
				}	
				continue;
			}	
			else{
				break;
			}
		}	
		if(statu==false){
			Assert.fail("Don't find the "+name+" element on the "+page+" page.");
			
		}




		

	}
    public boolean verifyDisplay(String page,String name){

		String selecttype = elementData.get(page).get(name).get("SelectType");
		String location = elementData.get(page).get(name).get("Location");
		log("To find the "+name+" element on the "+page+" page.");
		if (selecttype.equals("css")){
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(location)));
			} catch (Exception e) {
				// TODO: handle exception
				log("The "+name+" element on the "+page+" page is not displayed.");
				return false;
			}
			log("The "+name+" element on the "+page+" page is displayed.");
			sleep(500);			
			return true;

		}
		else if (selecttype.equals("id")){
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(location)));
			} catch (Exception e) {
				// TODO: handle exception
				log("The "+name+" element on the "+page+" page is not displayed.");
				return false;
			}
			
			
			log("The "+name+" element on the "+page+" page is displayed.");
			sleep(500);
			return true;
		}
		else if (selecttype.equals("xpath")){
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(location)));
			} catch (Exception e) {
				// TODO: handle exception
				log("The "+name+" element on the "+page+" page is not displayed.");
				return false;
			}

			log("The "+name+" element on the "+page+" page is displayed.");
			sleep(500);
			return true;
		}
		else if (selecttype.equals("linktext")){
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(location)));
			} catch (Exception e) {
				// TODO: handle exception
				log("The "+name+" element on the "+page+" page is not displayed.");
				return false;
			}
			
			log("The "+name+" element on the "+page+" page is displayed.");
			sleep(500);
			return true;
		}
		else if (selecttype.equals("name")){
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(location)));
			} catch (Exception e) {
				// TODO: handle exception
				log("The "+name+" element on the "+page+" page is not displayed.");
				return false;
			}
			
			log("The "+name+" element on the "+page+" page is displayed.");
			sleep(500);
			return true;
		}
		else if (selecttype.equals("partiallinktext")){
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(location)));
			} catch (Exception e) {
				// TODO: handle exception
				log("The "+name+" element on the "+page+" page is not displayed.");
				return false;
			}
						
			log("The "+name+" element on the "+page+" page is displayed.");
			sleep(500);
			return true;
		}
		else if (selecttype.equals("tagname")){
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(location)));
			} catch (Exception e) {
				// TODO: handle exception
				log("The "+name+" element on the "+page+" page is not displayed.");
				return false;
			}			

			log("The "+name+" element on the "+page+" page is displayed.");
			sleep(500);
			return true;
		}
		else if (selecttype.equals("index")){
			String[] sourceStrArray = location.split(",");
			String classname = sourceStrArray[0];
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(classname)));
			} catch (Exception e) {
				// TODO: handle exception
				log("The "+name+" element on the "+page+" page is not displayed.");
				return false;
			}	
			log("The "+name+" element on the "+page+" page is displayed.");
			sleep(500);
			return true;
		}	
		else {
			log("Don't support the type.");
			return false;
		}
    }
	public void getScreen(String filename){ 
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	  	if (!(new File(screenPath).isDirectory())) {  // 判断是否存在该目录
	  		new File(screenPath).mkdir();  // 如果不存在则新建一个目录
	  	}
		try {
			log("Get screen.");
			String path = screenPath+CommonTools.getCurrentTime()+ "_"+ filename+".png";
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log("Get screen failed.");
			Assert.fail("Get screen failed.");
		}		
	}
	
	private String getScreenReturnPath(String filename){ 
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	  	if (!(new File(screenPath).isDirectory())) {  // 判断是否存在该目录
	  		new File(screenPath).mkdir();  // 如果不存在则新建一个目录
	  	}
		try {
			log("Get screen.");
			String path = screenPath+CommonTools.getCurrentTime()+ "_"+ filename+".png";
			FileUtils.copyFile(scrFile, new File(path));
			return path;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log("Get screen failed.");
			Assert.fail("Get screen failed.");

		}		
		return null;
		
	}
	
	public void getScreen(){ 
		getScreen("");
	}
	private String getScreenReturnPath(){ 
		return getScreenReturnPath("");		
	}

	public void getElementScreen(String page,String name){
		String srcImg = getScreenReturnPath();
		File srcImg1 = new File(srcImg);
		String destImg = screenPath;
		String imgName = srcImg1.getName();
		int x = getElementLocateX(page, name);
		int y = getElementLocateY(page, name);
		int elementX =getElementX(page, name);
		int elementY = getElementY(page, name);

		ImageUtils.cutImage(srcImg, destImg+"cut by element_ "+name+" "+imgName, x, y, elementX, elementY);
	}
	
	public void getScreenMarkedByText(String content){
		
		String srcImg = getScreenReturnPath();
		File srcImg1 = new File(srcImg);
		String destImg = screenPath;
		String name = srcImg1.getName();
		ImageUtils.markImageByText(srcImg, destImg+"marked by text "+content+" "+name, content,Color.red,"黑体",13);
	}
	
	
    public int getElementX(String page,String name){
    	return findElement(page, name).getSize().getWidth();    	
    }
    
    public int getElementY(String page,String name){
    	return findElement(page, name).getSize().getHeight();  	
    }
	public int getElementLocateX(String page,String name){
		return findElement(page, name).getLocation().getX();
	}
	
	public int getElementLocateY(String page,String name){
		return findElement(page, name).getLocation().getY();
	}

}
