package com.scj.interceptor;

import com.scj.common.CommonConstants;
import com.scj.context.BlogContext;
import com.scj.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shengcj on 2016/7/29.
 */
public class LoginStatusInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginStatusInterceptor.class);

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        String uid = null;
        if (session.getAttribute(CommonConstants.USER_ID_ENCODE)!=null&&(uid = session.getAttribute(CommonConstants.USER_ID_ENCODE).toString()) != null&&isUIDCorrect(uid)) {
            return true;
        }

        Cookie[] cookies = request.getCookies();
        Cookie userCookie = null;
        for (Cookie cookie : cookies) {
            if (CommonConstants.USER_ID_ENCODE.equals(cookie.getName())) {
                userCookie = cookie;
                break;
            }
        }
        if (userCookie == null) {
            // TODO: 2016/7/29 可以把这些路径放到一个公共类中
            LOGGER.info("访问{}需要登录态",request.getRequestURI());
            response.sendRedirect(request.getContextPath() + "/user/view/login");
        } else {
            uid = userCookie.getValue();

            return isUIDCorrect(uid);
        }

        return false;
    }

    private boolean isUIDCorrect(String uid)
    {
        String[] params = uid.split("\\|");
        if (params.length >= 3) {
            String username = params[0];
            String expireTime = params[1];
            int hash = Integer.parseInt(params[2]);
            String password = userService.getUserByUsername(username).getPassword();

            if (hash == generateUIDHash(username, password, expireTime, BlogContext.getSalt())) {
                return true;
            }
        }

        return false;
    }

    // TODO: 2016/7/29 uid 代码公用化
    public String generateUID(String username, String password, Date datetime, String salt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetimeFormat = sdf.format(datetime);

        int hash = (username + datetimeFormat + password.substring(0, 3) + salt).hashCode();

        return username + "|" + datetimeFormat + "|" + hash;
    }

    public int generateUIDHash(String username, String password, String datetimeFormat, String salt) {
        return (username + datetimeFormat + password.substring(0, 3) + salt).hashCode();
    }
}