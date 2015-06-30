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

}
