package com.secangkirkopipanas.cstest.util;

import com.secangkirkopipanas.cstest.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    public static String format(long timestamp, String pattern) {
        return format(timestamp, pattern, Constants.TIMEZONE);
    }

    public static String format(long timestamp, String pattern, String timezone) {
        Date date = new Date(timestamp);
        DateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(TimeZone.getTimeZone(timezone));
        return format.format(date);
    }

}
