package com.scj.service.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.util.calendar.LocalGregorianCalendar;

import java.util.Date;

/**
 * Created by shengcj on 2016/7/28.
 */
public class LogTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void  testLog1()
    {
        LOGGER.debug("debug 123");
        LOGGER.info("info 123");
        LOGGER.warn("warn 123");
        LOGGER.error("error 123");

        LOGGER.info("test time :{}",new Date());
    }
}
