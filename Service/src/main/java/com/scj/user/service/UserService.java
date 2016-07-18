package com.scj.user.service;

import com.scj.user.entity.User;

/**
 * Created by Administrator on 2016/7/11.
 */
public interface UserService {

    boolean register(User user);

    boolean login(String username,String password);

    boolean isUserExisted(String username);
}
