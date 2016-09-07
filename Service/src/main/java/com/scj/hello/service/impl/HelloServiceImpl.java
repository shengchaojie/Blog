package com.scj.hello.service.impl;

import com.scj.hello.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/30.
 */

@Service
public class HelloServiceImpl implements HelloService{
    @Override
    public boolean hello(String name) {
        System.out.println("hello" +name);
        return true;
    }
}
