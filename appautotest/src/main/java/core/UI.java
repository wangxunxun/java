package core;

import java.util.Map;




import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import core.Initial;
public class UI extends Initial{
    
    public void clickElement(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
    	findElement(eledata, page, name).click();
    }
    
    public void sendKeys(Map<String, Map<String, Map<String, String>>> eledata,String page,String name,String value){
    	findElement(eledata, page, name).sendKeys(value);
    }
    
    public String getElementText(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
    	String value = findElement(eledata, page, name).getText();
    	if(value !=null){
    		return value;
    	}
    	else{
    		tool.log(name+":No text.");
    	}
    	return null;
    }
    
    protected void executeJavaScript(String js, WebElement element) {
        ((JavascriptExecutor) driver).executeScript(js, element);
    }
    
    public void assertEquals(String actual, String expected){
    	Assert.assertEquals(actual, expected);
    }
    

    
    public boolean isSlected(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
    	return findElement(eledata, page, name).isSelected();
    }
    
    public boolean isDisplayed(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
    	return findElement(eledata, page, name).isDisplayed();
    }
    
    public boolean isEnabled(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
    	return findElement(eledata, page, name).isEnabled();
    }
    


	public WebElement findElement(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
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
	
    public void waitDisplay(Map<String, Map<String, Map<String, String>>> eledata,String page,String name){
		String selecttype = eledata.get(page).get(name).get("SelectType");
		String location = eledata.get(page).get(name).get("Location");
		if (selecttype.equals("css")){
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(location)));

		}
		else if (selecttype.equals("id")){
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(location)));
		}
		else if (selecttype.equals("xpath")){
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(location)));
		}
		else if (selecttype.equals("linktext")){
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(location)));
		}
		else if (selecttype.equals("name")){
			wait.until(ExpectedConditions.presenceOfElementLocated(By.name(location)));
		}
		else if (selecttype.equals("partiallinktext")){
			wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(location)));
		}
		else if (selecttype.equals("tagname")){
			wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(location)));
		}
		else if (selecttype.equals("index")){
			String[] sourceStrArray = location.split(",");
			String classname = sourceStrArray[0];
			String index = sourceStrArray[1];
			int in=Integer.parseInt(index);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(classname)));
		}	
		else {
			System.out.println("Can not find the element.");
		}
    }

}
