package com.ithiema.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* @Author: 吴将
 * \* Time: 2020/5/31 22:10
 * \* Description:
 * \
 */
public class DateUtils {

    //日期转换成字符串
    public static String date2String(Date date, String patt){
        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        return sdf.format(date);
    }

    //字符串转换成日期
    public static Date string2Date(String date,String patt) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        return sdf.parse(date);
    }
}
