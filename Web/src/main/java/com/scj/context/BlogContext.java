package com.scj.context;

/**
 * Created by shengcj on 2016/7/29.
 */
public class BlogContext {
    //用来加密
    private static final String salt ="123456";

    public static String getSalt() {
        return salt;
    }

}
