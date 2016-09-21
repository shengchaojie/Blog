package com.scj.controller;


import com.scj.common.exception.BusinessException;
import com.scj.common.exception.StatusCode;
import com.scj.common.util.EncodeDecodeUtil;
import com.scj.hello.service.HelloService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
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
        return "index";
    }

    @RequestMapping("/exception")
    public void exception()
    {
        throw new BusinessException(StatusCode.USER_NOT_EXISTED);
    }

    @RequestMapping("/**")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/danmu")
    public String danmu()
    {
        return "danmu";
    }

    @RequestMapping("/note")
    public String note()
    {
        return "/note/note";
    }

    @RequestMapping("/noteAdd")
    public String noteAdd()
    {
        return "/note/note_add";
    }

    @RequestMapping("/cache")
    public ResponseEntity<String> cache(
            HttpServletRequest request,
            @RequestParam("millis")long lastModifiedMillis,//为了方便测试，此处传入文档最后修改时间
            @RequestHeader(value = "If-Modified-Since",required = false)String ifModifiedSince) throws ParseException {
        //这里使用string来接受ifModifiedSince在转换为时间是因为 可能框架自带的时间转换会把时区信息给忽略掉 导致误差
        //还是自己手动转换好了 默认的时区是CST的 但是在jvm设置为GMT+8后 时间转换还是出现问题
        long now = System.currentTimeMillis();
        long maxAge =3600;//max-age单位是秒，穿进来的millis是毫秒，所以需要除以1000，忽略毫秒的精度

        SimpleDateFormat sdf =new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);

        if(ifModifiedSince!=null)
        {
            Date ifModifiedSinceDate =sdf.parse(ifModifiedSince);
            LOGGER.debug("ifModifiedSince:{}",ifModifiedSince);
            LOGGER.debug("ifModifiedSince:{}",ifModifiedSinceDate.getTime());
            LOGGER.debug("lastModifiedMillis:{}",lastModifiedMillis);
            if(ifModifiedSinceDate.getTime()/1000>=lastModifiedMillis/1000) //最后三位为毫秒 转换的时候失去精度
                return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        }

        String body="<a href=''>点击访问当前链接("+new Date() +")</a>";
        MultiValueMap<String,String> headers =new HttpHeaders();

        headers.add(HttpHeaders.LAST_MODIFIED,sdf.format(new Date(lastModifiedMillis)));
        headers.add(HttpHeaders.DATE,sdf.format(new Date(now)));
        headers.add(HttpHeaders.EXPIRES,sdf.format(new Date(now+maxAge*1000)));//HTTP1.O
        headers.add(HttpHeaders.CACHE_CONTROL,"max-age="+maxAge);//HTTP1.1
        headers.add(HttpHeaders.CONTENT_TYPE,"text/html;charset=UTF-8");

        return new ResponseEntity<String>(body,headers,HttpStatus.OK);
    }

    @RequestMapping("/cache/etag")
    public ResponseEntity<String> eTag(
            HttpServletRequest request,
            @RequestHeader(value = "If-None-Match",required = false)String ifNoneMatch)
    {
        long now = System.currentTimeMillis();
        long maxAge =3600;
        SimpleDateFormat sdf =new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);

        //String body="<a href=''>点击访问当前链接("+new Date() +")</a>";
        String body="<a href=''>点击访问当前链接()</a>";
        String eTag = EncodeDecodeUtil.encodeWithMD5(body);
        LOGGER.debug("ifNoneMatch:{}",ifNoneMatch);
        LOGGER.debug("etag:{}",eTag);

        if(ifNoneMatch!=null&& StringUtils.equals(ifNoneMatch,eTag))
        {
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        }

        MultiValueMap<String,String> headers =new HttpHeaders();
        headers.add(HttpHeaders.ETAG,eTag);
        headers.add(HttpHeaders.DATE,sdf.format(new Date(now)));
        headers.add(HttpHeaders.CONTENT_TYPE, "text/html;charset=utf-8");
        headers.add(HttpHeaders.CACHE_CONTROL,"max-age="+maxAge);

        return new ResponseEntity<String>(body,headers,HttpStatus.OK);
    }
}
