package com.scj.controller;


import com.scj.common.exception.BusinessException;
import com.scj.common.exception.StatusCode;
import com.scj.hello.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/6/30.
 */

@Controller
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

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

    @RequestMapping("/cache")
    public ResponseEntity<String> cache(
            HttpServletRequest request,
            @RequestParam("millis")long lastModifiedMillis,//为了方便测试，此处传入文档最后修改时间
            @RequestHeader(value = "If-Modified-Since",required = false)Date ifModifiedSince)
    {
        long now = System.currentTimeMillis();
        long maxAge =3600;

        if(ifModifiedSince!=null)
        {
            LOGGER.info("ifModifiedSince:{}",ifModifiedSince);
            LOGGER.info("ifModifiedSince:{}",ifModifiedSince.getTime());
            LOGGER.info("lastModifiedMillis:{}",lastModifiedMillis);
            if(ifModifiedSince.getTime()>=lastModifiedMillis)
                return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        }

        String body="<a href=''>点击访问当前链接</a>";
        MultiValueMap<String,String> headers =new HttpHeaders();
        SimpleDateFormat sdf =new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);

        headers.add(HttpHeaders.LAST_MODIFIED,sdf.format(new Date(lastModifiedMillis)));
        headers.add(HttpHeaders.DATE,sdf.format(new Date(now)));
        //headers.add(HttpHeaders.EXPIRES,sdf.format(new Date(now+maxAge*1000)));
        headers.add(HttpHeaders.CACHE_CONTROL,"max-age="+maxAge);
        headers.add(HttpHeaders.CONTENT_TYPE,"text/html;charset=UTF-8");

        return new ResponseEntity<String>(body,headers,HttpStatus.OK);
    }
}
