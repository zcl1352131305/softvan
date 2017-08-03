package cn.com.softvan.utils;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/6/23 0023.
 */
public class DateUtil {
    public static String getNowDate(){
        Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DATE);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int hour = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        return year+"/"+month+"/"+date+" "+hour+":"+min+":"+sec;
    }
}
