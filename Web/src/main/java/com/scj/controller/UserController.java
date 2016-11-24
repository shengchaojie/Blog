package com.scj.controller;

import com.scj.bean.UserVO;
import com.scj.common.CommonConstants;
import com.scj.common.exception.StatusCode;
import com.scj.common.util.AssertUtils;
import com.scj.context.BlogContext;
import com.scj.context.ResponseResult;
import com.scj.user.entity.User;
import com.scj.user.entity.UserInfo;
import com.scj.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by shengchaojie on 2016/7/18.
 */

@Controller
@RequestMapping("/user")
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

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
    public String login(String username, String password, HttpSession session, HttpServletRequest request, HttpServletResponse response)
    {
        AssertUtils.isStringEmpty(username);
        AssertUtils.isStringEmpty(password);

        User user =null;
        if((user=userService.login(username,password))!= null)
        {
            //uid=登录名|有效时间Expires|hash值。
            // hash值可以由"登录名+有效时间Expires+用户密码（加密后的）的前几位 +salt"
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar =Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH,2);
            Date expireTime =calendar.getTime();
            String expireTimeFormat =sdf.format(expireTime);

            int hash =(user.getUsername()+expireTimeFormat+user.getPassword().substring(0,3)+ BlogContext.getSalt()).hashCode();
            String uid =user.getUsername()+"|"+expireTimeFormat+"|"+hash;

            Cookie cookie =new Cookie(CommonConstants.USER_ID_ENCODE,uid);
            cookie.setPath("/");
            cookie.setDomain(".shengchaojie.com");
            LOGGER.debug("设置cookie Domain:{}",request.getServerName());
            response.addCookie(cookie);
            session.setAttribute(CommonConstants.USER_ID_ENCODE,uid);
            session.setAttribute(CommonConstants.USER_ID,user.getId());
            session.setAttribute(CommonConstants.USER_NAME,user.getNickname());

            //用户权限登陆时长控制 需要在研究->设置session有效时间 在拦截器里面验证
            // TODO: 2016/7/29  都cookie免登陆了 还需要这个干吗？

            return "/index";
        }else {
            return "/error/error";
        }
    }

    @RequestMapping(path = "loginModal",method = RequestMethod.POST)
    @ResponseBody
    private ResponseResult<String> loginInModal(String username, String password, HttpSession session, HttpServletRequest request, HttpServletResponse response)
    {
        // TODO: 2016/11/24 这两个方法需要抽象一下
        AssertUtils.isStringEmpty(username);
        AssertUtils.isStringEmpty(password);

        User user =null;
        if((user=userService.login(username,password))!= null)
        {
            //uid=登录名|有效时间Expires|hash值。
            // hash值可以由"登录名+有效时间Expires+用户密码（加密后的）的前几位 +salt"
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar =Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH,2);
            Date expireTime =calendar.getTime();
            String expireTimeFormat =sdf.format(expireTime);

            int hash =(user.getUsername()+expireTimeFormat+user.getPassword().substring(0,3)+ BlogContext.getSalt()).hashCode();
            String uid =user.getUsername()+"|"+expireTimeFormat+"|"+hash;

            Cookie cookie =new Cookie(CommonConstants.USER_ID_ENCODE,uid);
            cookie.setPath("/");
            cookie.setDomain(".shengchaojie.com");
            LOGGER.debug("设置cookie Domain:{}",request.getServerName());
            response.addCookie(cookie);
            session.setAttribute(CommonConstants.USER_ID_ENCODE,uid);
            session.setAttribute(CommonConstants.USER_ID,user.getId());
            session.setAttribute(CommonConstants.USER_NAME,user.getNickname());

            //用户权限登陆时长控制 需要在研究->设置session有效时间 在拦截器里面验证
            // TODO: 2016/7/29  都cookie免登陆了 还需要这个干吗？

            return new ResponseResult<>(StatusCode.OK,user.getNickname());//就先把用户的nickname返回
        }else {
            return new ResponseResult<>(StatusCode.USERNAME_PASSWORD_WRONG);
        }
    }

    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public String register(UserVO userVO,HttpSession session)
    {
        User user =new User();
        user.setUsername(userVO.getUsername());
        user.setPassword(userVO.getPassword());
        user.setNickname(userVO.getNickname());

        UserInfo userInfo =new UserInfo();
        userInfo.setUser(user);
        userInfo.setAge(userVO.getAge());
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        try {
            userInfo.setBirth(sdf.parse(userVO.getBirth()));
        }catch (Exception e)
        {}
        userInfo.setGender(userVO.getGender());

        user.setUserInfo(userInfo);

        if(userService.register(user))
        {
            //注册成功后跳转到登录界面
            return "/index";
        }else
        {
            return "/error/error";
        }
    }

    @RequestMapping(path="/logout",method = RequestMethod.GET)
    public String logout(HttpServletResponse response,HttpSession session)
    {
        if(session!=null)
        {
            session.invalidate();
        }
        //让cookie失效
        Cookie cookie =new Cookie(CommonConstants.USER_ID_ENCODE,"");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "/index";
    }

    @RequestMapping(path = "isUserNameExisted",method = RequestMethod.GET)
    @ResponseBody
    public String isUserNameExisted(String username)
    {
        if(userService.isUserExisted(username))
        {
            return "false";
        }else
        {
            return "true";
        }
    }
}
