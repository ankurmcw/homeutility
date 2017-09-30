package com.mcw.homeutility.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.mcw.homeutility.utility.AppConstants.DATE_FORMAT;
/**
 * Created by renuka on 30/9/17.
 */
public class CommonUtils {

    public static Date getDate(String source) throws ParseException {
        return new SimpleDateFormat(DATE_FORMAT).parse(source);
    }

    public static String getDate(Date source) throws ParseException {
        return new SimpleDateFormat(DATE_FORMAT).format(source);
    }
}
