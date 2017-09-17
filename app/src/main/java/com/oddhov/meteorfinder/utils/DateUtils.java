package com.oddhov.meteorfinder.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by sammy on 17/09/17.
 */

public class DateUtils {
    public Date getYearFromDateString(String dateString) {
        //Example: 2012-01-01T00:00:00.000
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date date;
        try {
            if (dateString != null) {
                date = df.parse(dateString);
                return date;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getYearFromDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return String.valueOf(calendar.get(Calendar.YEAR));
    }
}
