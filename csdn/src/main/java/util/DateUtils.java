package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final long YEAR_TIME = 1000L * 60 * 60 * 24 * 365;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT2 = new SimpleDateFormat("yyyy-MM-dd");

    public static SimpleDateFormat getSimpleDateFormat1() {
        return SIMPLE_DATE_FORMAT1;
    }

    public static SimpleDateFormat getSimpleDateFormat2() {
        return SIMPLE_DATE_FORMAT2;
    }

    public static int maAge(long uid) {
        long time = new Date().getTime() - uid - 1661097600000L;
        int age = (int) (time / YEAR_TIME);
        if (time % YEAR_TIME != 0){
            return age+1;
        }else{
            return age;
        }
    }

    public static String getDate1(){
        return SIMPLE_DATE_FORMAT1.format(new Date());
    }

    public static String getDate2(){
        return SIMPLE_DATE_FORMAT2.format(new Date());
    }

}
