package com.nju.edu.erp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date string2DateUtil(String dateStr){
        SimpleDateFormat ft1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat ft2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date result;
        try {
            result = ft1.parse(dateStr);
        } catch (Exception e) {
            try{
                result = ft2.parse(dateStr);
            } catch (Exception e2) {
                result = null;
            }
        }
        return result;
    }
}
