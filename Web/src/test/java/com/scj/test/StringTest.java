package com.scj.test;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by shengcj on 2016/7/29.
 */
public class StringTest {
    @Test
    public void testSpilt()
    {
        String s ="133|2 33|3333";
        String[] ss =s.split("\\|");
        for(String a :ss)
        {
            System.out.println(a);
        }
    }
}
