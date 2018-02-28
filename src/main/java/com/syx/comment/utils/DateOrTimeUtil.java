package com.syx.comment.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Msater Zg
 */
@Component
public class DateOrTimeUtil {
    public static String getNowTimeByDifferentFormat(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date());
    }

    public List<String> computingTime(String startTime, String endTime) {
        int len = daysBetween(startTime, endTime);
        List list = new ArrayList();
        list.add(startTime);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = f.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        for (int i = 0; i < len; i++) {
            c.add(Calendar.DAY_OF_MONTH, 1);
            Date tomorrow = c.getTime();
            list.add(f.format(tomorrow));
        }
        return list;
    }

    public int daysBetween(String startTime, String endTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long start = date.getTime();
        Date dateEnd = null;
        try {
            dateEnd = simpleDateFormat.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long end = dateEnd.getTime();
        int n = (int) ((end - start) / (1000 * 60 * 60 * 24));
        return n;
    }
}
