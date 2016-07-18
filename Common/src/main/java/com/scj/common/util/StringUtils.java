package com.scj.common.util;

/**
 * Created by Administrator on 2016/7/11.
 */
public class StringUtils {
    public static boolean isEmpty(String s)
    {
        return s==null||s.trim().length()==0;
    }

    public static String reverse(String text)
    {
        return new StringBuilder(text).reverse().toString();
    }
}
