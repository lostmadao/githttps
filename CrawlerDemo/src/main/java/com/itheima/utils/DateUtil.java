package com.itheima.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String dateParse(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateFormat.format(date);
        return dateStr;
    }
}
