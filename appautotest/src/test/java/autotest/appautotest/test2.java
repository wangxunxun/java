package autotest.appautotest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class test2 extends webtest {

	

    
    @DataProvider(name = "input")
    public Object[][] input(){
    	return new Object[][]{
    			{"ceshi"},{"kaifa"}
    	};
    }
    
    
    
    @Test(dataProvider = "input")
    public void baidu(String haha) throws InterruptedException, IOException{
    	Thread.sleep(5000);
		String url="http://www.baidu.com";
		driver.get(url);
		takescreenshot();
		driver.findElement(By.cssSelector("#kw")).sendKeys(haha);
		driver.findElement(By.cssSelector("#su")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#s_tab > b")));
		Assert.assertEquals( driver.findElement(By.cssSelector("#s_tab > b")).getText(),"网页");	
		Thread.sleep(5000);
		System.out.println("ok");
		

    }
    
    @Test(dependsOnMethods = {"guanyubaidu"})
    public void xinwen() throws InterruptedException{
    	Thread.sleep(5000);

    	
    }

    
    @Test
    public void guanyubaidu() throws InterruptedException{
    	String url="http://www.baidu.com";
		driver.get(url);
		Thread.sleep(5000);

		driver.findElement(By.cssSelector("#lh > a:nth-child(3)")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("#nav > li.li6 > a")).getText(), "联系我们");
		Thread.sleep(5000);
		
    }
}
