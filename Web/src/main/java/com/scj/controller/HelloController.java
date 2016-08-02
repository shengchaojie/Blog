package com.scj.controller;


import com.scj.common.exception.BusinessException;
import com.scj.common.exception.StatusCode;
import com.scj.hello.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.BASE64Decoder;

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
        //helloService.hello("scj");
        /*if(1==1)
        {
            //throw new RuntimeException("123");
            throw new BusinessException(StatusCode.USER_NOT_EXISTED);
        }*/

        return "index";
    }

    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/danmu")
    public String danmu()
    {
        return "danmu";
    }
}
