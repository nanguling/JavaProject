package com.mystudy.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OutPutUtil {
    public static String formatLength(Long length) {
        if (length < 1024) {
            return length + "Byte";
        }
        if (length < 1024*1024) {
            return (length / 1024) + "KB";
        }
        if (length < 1024*1024*1024) {
            return (length / 1024 / 1024) + "MB";
        }
        return (length / 1024 / 1024 / 1024) + "GB";
    }

    public static String formatTimstamp(Long lastModifiedTimeStamp) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date(lastModifiedTimeStamp);
        return format.format(date);
    }
}
