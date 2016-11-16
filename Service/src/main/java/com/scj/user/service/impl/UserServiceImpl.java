package com.scj.user.service.impl;

import com.scj.user.entity.User;
import com.scj.common.exception.BusinessException;
import com.scj.common.exception.StatusCode;
import com.scj.common.util.AssertUtils;
import com.scj.common.util.EncodeDecodeUtil;
import com.scj.user.repository.UserInfoRepository;
import com.scj.user.repository.UserRepository;
import com.scj.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/7/11.
 */
@Component
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public boolean register(User user) {
        AssertUtils.isStringEmpty(user.getUsername());
        AssertUtils.isStringEmpty(user.getPassword());

        //判断用户是否已经注册
        if(isUserExisted(user.getUsername()))
        {
            throw new BusinessException(StatusCode.USER_REGISTERED_ALREADY);
        }

        //对密码进行处理
        user.setPassword(EncodeDecodeUtil.EncodePassword(user.getPassword()));

        return userRepository.save(user) !=null;
        /*if(userRepository.save(user) !=null&&user.getUserInfo()!=null)
        {
            return user.getUserInfo() == null || userInfoRepository.save(user.getUserInfo()) != null;
        }else {
            return false;
        }*/
    }

    @Override
    public User login(String username, String password) {
        AssertUtils.isStringEmpty(username);
        AssertUtils.isStringEmpty(password);

        //判断用用户是否存在
        if(!isUserExisted(username))
        {
            throw new BusinessException(StatusCode.USER_NOT_EXISTED);
        }

        User user =null;
        //登陆校验
        if((user=userRepository.findByUsernameAndPassword(username,EncodeDecodeUtil.EncodePassword(password)))!=null)
        {
            return user;
        }else
        {
            throw new BusinessException(StatusCode.USERNAME_PASSWORD_WRONG);
        }

    }

    @Override
    public boolean isUserExisted(String username) {
        return userRepository.findByUsername(username).size()>0;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).get(0);
    }

    @Override
    public User getUserById(Integer integer) {
        return userRepository.findOne(integer);
    }
}
