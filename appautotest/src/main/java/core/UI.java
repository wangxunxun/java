package core;

import core.Initial;
public class UI extends Initial {
	public void enterurl(String url) throws InterruptedException{
		driver.get(url);
		Thread.sleep(5000);
	}
		
}
