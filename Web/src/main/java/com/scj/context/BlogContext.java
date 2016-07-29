package com.scj.context;

import com.scj.user.entity.User;

/**
 * Created by shengcj on 2016/7/29.
 */
public class BlogContext {
    //用来加密
    private static final String salt ="123456";

    private static User loginUser;

    public static String getSalt() {
        return salt;
    }

    public static User getLoginUser() {
        return loginUser;
    }

    public static void setLoginUser(User loginUser) {
        BlogContext.loginUser = loginUser;
    }
}
