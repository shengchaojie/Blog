package com.scj.interceptor;

import java.util.Date;

/**
 * Created by shengcj on 2016/7/26.
 */
public class InterfaceInfo {
    //接口调用url
    private String url;
    //接口执行时间
    private Date invokeTime;
    //调用者ip
    private String ipAddress;
    //调用者用户名,后期需要，现在没有用户系统
    private String username;

    public InterfaceInfo() {
    }

    public InterfaceInfo(String url, Date invokeTime, String ipAddress) {
        this.url = url;
        this.invokeTime = invokeTime;
        this.ipAddress = ipAddress;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getInvokeTime() {
        return invokeTime;
    }

    public void setInvokeTime(Date invokeTime) {
        this.invokeTime = invokeTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
