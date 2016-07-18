package com.scj.service.test;

import com.scj.user.service.TalkService;
import org.junit.Test;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;

/**
 * Created by shengchaojie on 2016/7/17.
 */
public class TalkServiceTest extends TestBase{

    @Resource
    private TalkService talkService;

    @Test
    public void getTalks()
    {
        talkService.getTalks(9).stream().forEach(System.out::println);
    }
}
