package com.syx.comment.utils;

/**
 * @author Msater Zg
 */
public class SqlEasy {
    public static String limitPage(String pageSize,String pageNumber){
        int pageNumberInt = Integer.parseInt(pageNumber, 10);
        int pageSizeInt = Integer.parseInt(pageSize, 10);
        return " LIMIT " + ((pageNumberInt - 1) * pageSizeInt) + "," + pageSizeInt + " ";
    }
}
