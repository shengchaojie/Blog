package com.scj.test;

import org.hibernate.internal.util.compare.CalendarComparator;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by shengchaojie on 16-7-27.
 */
public class DateTest {

    @Test
    public void testTimeLong()
    {
        Calendar calendar =Calendar.getInstance();
        Date date1 =calendar.getTime();
        System.out.println(date1.getTime());
        calendar.add(Calendar.SECOND,5);
        Date date2 =calendar.getTime();
        System.out.println(date2.getTime());
    }
}
