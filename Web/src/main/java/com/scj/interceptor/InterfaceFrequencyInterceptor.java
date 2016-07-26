package com.scj.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by shengcj on 2016/7/26.
 * 对评论类接口进行频率控制
 */
public class InterfaceFrequencyInterceptor implements HandlerInterceptor {
    //第一层url 第二层ip，以后是username
    private static Map<String,Map<String,List<InterfaceInfo>>> interfaceInfoMap =new HashMap<>();

    //同一ip接口调用间隔时间
    private final static long INTERFACE_INVOKE_INTERVAL =5*1000;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url =httpServletRequest.getContextPath();
        String ipAddress =httpServletRequest.getRemoteAddr();

        InterfaceInfo interfaceInfo =new InterfaceInfo(url,new Date(),ipAddress);

        //先插入接口信息map，然后进行统一ip调用频率控制
        List<InterfaceInfo> interfaceInfoList =null;
        if((interfaceInfoList =interfaceInfoMap.get(url).get(ipAddress))==null)
        {
            //接口第一次调用不做判断
            Map<String,List<InterfaceInfo>> ipMap =new HashMap<>();
            List<InterfaceInfo> infoList =new ArrayList<>();
            infoList.add(interfaceInfo);
            ipMap.put(ipAddress,infoList);
            interfaceInfoMap.put(url,ipMap);
        }else
        {
            //拿到最新一条信息
            InterfaceInfo lastestInfo =interfaceInfoList.get(interfaceInfoList.size()-1);
            long interval = interfaceInfo.getInvokeTime().getTime() -lastestInfo.getInvokeTime().getTime();
            if(interval<INTERFACE_INVOKE_INTERVAL)
            {
                OutputStream outputStream= httpServletResponse.getOutputStream();
                outputStream.write("too many times".getBytes());
                outputStream.flush();
                outputStream.close();
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
