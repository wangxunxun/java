package autotest.appautotest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonTools {
    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
        return f.format(date);
    }
    
    public static String getCurrentPath(String dirname){
    	String currentPath = System.getProperty("user.dir");
    	return currentPath + dirname;
    }

    
    protected static void log(String content, Integer type) {

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
	
    public static void log(String content) {

        log(content, 1);
    }
    
    public static void waitByTimeOut(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
