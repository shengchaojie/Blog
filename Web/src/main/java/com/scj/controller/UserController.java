package com.scj.controller;

import com.bean.UserVO;
import com.scj.common.util.AssertUtils;
import com.scj.user.entity.User;
import com.scj.user.service.UserService;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by shengchaojie on 2016/7/18.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(path = "/view/login",method = RequestMethod.GET)
    public String login()
    {
        return "/user/login";
    }

    @RequestMapping(path="/view/register",method = RequestMethod.GET)
    public String register()
    {
        return "/user/register";
    }

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session)
    {
        AssertUtils.isStringEmpty(username);
        AssertUtils.isStringEmpty(password);

        if(userService.login(username,password))
        {
            session.setAttribute("username",username);//用户权限登陆时长控制 需要在研究
            return "/index";
        }else {
            return "/error/error";
        }
    }

    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public String register(UserVO userVO,HttpSession session)
    {
        User user =new User();
        user.setUsername(userVO.getUsername());
        user.setPassword(userVO.getPassword());
        user.setNickname(userVO.getNickname());

        if(userService.register(user))
        {
            //注册成功后直接登陆
            session.setAttribute("username",userVO.getUsername());
            return "/index";
        }else
        {
            return "/error/error";
        }
    }
}
