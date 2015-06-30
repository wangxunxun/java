package autotest.appautotest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonTools {
    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss.SSS");
        return f.format(date);
    }

}
