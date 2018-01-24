package com.syx.comment.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Msater Zg
 */
@Component
public class DateOrTimeUtil {
    public static String getNowTimeByDifferentFormat(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date());
    }
}
