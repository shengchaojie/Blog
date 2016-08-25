package com.scj.filter;

import com.sun.xml.internal.xsom.impl.Ref;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.CharSet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by shengcj on 2016/8/5.
 */
public class TestFrequencyFilter extends FrequencyFilter{

    @Override
    public String getUserName(HttpServletRequest httpServletRequest) {
        return "testName";
    }

    @Override
    public void handleUnitTimeNotPass(HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding(CharEncoding.UTF_8);
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
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding(CharEncoding.UTF_8);
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

   /* @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("before");
        filterChain.doFilter(servletRequest,servletResponse);
        return;
    }*/
}
