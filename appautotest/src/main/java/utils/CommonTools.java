package utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class CommonTools {
	Properties PROPERTIES = new Properties(System.getProperties());	
    
    public String getOSName(){
    	return PROPERTIES.getProperty("os.name");
    }
    
    public String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
        return f.format(date);
    }
    
    public String setPath(String dirname){
    	String currentPath = System.getProperty("user.dir");
    	return currentPath + dirname;
    }

    
    protected void log(Object content, Integer type) {

        switch (type) {
        case 1: {
            System.out.println(getCurrentTime() + " INFO - " + content);
            break;
        }
        case 2: {
            System.err.println(getCurrentTime() + " ERROR - " + content);
            break;
        }
        case 3: {
            System.out.println(getCurrentTime() + " WARNING - " + content);
            break;
        }
        case 4: {
            System.err.println(getCurrentTime() + " WARNING - " + content);
            break;
        }
       }

    }
	
    public void log(Object content) {

        log(content, 1);
    }
    
    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
