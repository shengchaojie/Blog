package com.scj.service.test;

import com.scj.hello.aspect.ByeService;
import com.scj.hello.service.HelloService;
import com.scj.user.service.TalkService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;

/**
 * Created by shengchaojie on 2016/7/17.
 */
public class TalkServiceTest extends TestBase{
    private static final Logger LOGGER = LoggerFactory.getLogger(TalkServiceTest.class);

    @Resource
    private TalkService talkService;

    @Resource
    private HelloService helloService;

    @Resource
    private ByeService byeService;

    @Test
    public void getTalks()
    {
        talkService.getTalks(9).stream().forEach(System.out::println);
    }

    @Test
    public void testAop()
    {
        helloService.hello("123");
    }

    @Test
    public void testAopInject()
    {
        System.out.println(byeService.getClass());
        byeService.sayBye();
    }
}
