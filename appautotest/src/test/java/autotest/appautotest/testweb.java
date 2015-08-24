package autotest.appautotest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import core.Initial;
import core.UI;

public class testweb extends UI {


	@Test
    public void rrr() throws InterruptedException{
    	String url="http://www.baidu.com";
		enterurl(url);

		driver.findElement(By.cssSelector("#lh > a:nth-child(3)")).click();
//		Assert.assertEquals(driver.findElement(By.cssSelector("#nav > li.li6 > a")).getText(), "联系我们");
		Thread.sleep(5000);
		
    }
}
