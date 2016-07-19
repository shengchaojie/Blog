package com.scj.controller;


import com.scj.hello.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/6/30.
 */

@Controller
public class HelloController {

    @Resource
    private HelloService helloService;

    @RequestMapping("/hello")
    public String hello()
    {
        helloService.hello("scj");

        return "index";
    }

    @RequestMapping("/")
    public String index()
    {
        return "index";
    }
}
