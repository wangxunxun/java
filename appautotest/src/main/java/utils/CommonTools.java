package utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class CommonTools {
	static Properties PROPERTIES = new Properties(System.getProperties());	
    
    public static String getOSName(){
    	return PROPERTIES.getProperty("os.name");
    }
    
    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
        return f.format(date);
    }
    
    public static String setPath(String dirname){
    	String currentPath = System.getProperty("user.dir");
    	return currentPath + dirname;
    }

    public static void deleteFile(String filePath){
		File f = new File(filePath);
		if(f.exists()){
			f.delete();
		}
	}
    
    protected static void log(Object content, Integer type) {

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
	
    public static void log(Object content) {

        log(content, 1);
    }
    
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private static Properties getConfigFormatData(String configFileName) {
        try {
            Properties pro = new Properties();
            FileInputStream fis = new FileInputStream(configFileName);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader brProp = new BufferedReader(isr);
            pro.load(brProp);
            brProp.close();
            return pro;
        } catch (Exception e) {
            throw new IllegalStateException("Can't locate config file " + configFileName, e);
        }
    }

	public static String getProperties(String configFileName,String name){  
		
    	String currentPath = System.getProperty("user.dir");
    	configFileName = currentPath+configFileName;
		Properties properties = getConfigFormatData(configFileName); 
		return properties.getProperty(name);

    }  

}
