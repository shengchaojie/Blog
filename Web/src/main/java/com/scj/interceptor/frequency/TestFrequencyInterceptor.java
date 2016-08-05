package com.scj.interceptor.frequency;

import com.scj.interceptor.frequency.FrequencyInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by shengchaojie on 2016/8/3.
 */
public class TestFrequencyInterceptor extends FrequencyInterceptor {

    @Override
    public String getUserName(HttpServletRequest httpServletRequest) {
        return "Test-SCJ";
    }

    @Override
    public void handleUnitTimeNotPass(HttpServletResponse httpServletResponse) {
        try {
            OutputStream outputStream =httpServletResponse.getOutputStream();
            try {
                outputStream.write("接口调用次数太多".getBytes("UTF-8"));
            }finally {
                outputStream.flush();
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleIntervalNotPass(HttpServletResponse httpServletResponse) {
        try {
            OutputStream outputStream =httpServletResponse.getOutputStream();
            try {
                outputStream.write("接口调用太频繁".getBytes("UTF-8"));
            }finally {
                outputStream.flush();
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
