package autotest.appautotest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class test3 extends webtest{
	@Test
    public void rrr() throws InterruptedException{
    	String url="http://www.baidu.com";
		webtest.driver.get(url);
		Thread.sleep(5000);

		driver.findElement(By.cssSelector("#lh > a:nth-child(3)")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("#nav > li.li6 > a")).getText(), "联系我们");
		Thread.sleep(5000);
		
    }
}


