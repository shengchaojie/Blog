package com.scj.test;

import org.hibernate.internal.util.compare.CalendarComparator;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    @Test
    public void testDateFormat() throws ParseException {
        String a ="Sat, 10 Sep 2016 03:52:39 GMT";
        SimpleDateFormat sdf =new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);//需要加上后面那个才能转换成功
        System.out.println(sdf.parse(a));

    }
}
