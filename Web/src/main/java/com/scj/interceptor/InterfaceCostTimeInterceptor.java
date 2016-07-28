package com.scj.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by shengcj on 2016/7/28.
 * 接口调用数据日志 拦截器
 */
public class InterfaceCostTimeInterceptor implements HandlerInterceptor{
    private static final Logger LOGGER = LoggerFactory.getLogger(InterfaceCostTimeInterceptor.class);

    private Date date;
    private String ipAddress;
    private String url;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        date = Calendar.getInstance().getTime();
        ipAddress =httpServletRequest.getRemoteAddr();
        url =httpServletRequest.getRequestURI();

        LOGGER.info("【{}】 start call api :{} ,Time：{}",ipAddress,url,date);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        Date now =Calendar.getInstance().getTime();
        long ms = now.getTime() -date.getTime();

        LOGGER.info("【{}】 end call api :{} ,Time：{},Cost {} ms",ipAddress,url,now,ms);

    }
}
