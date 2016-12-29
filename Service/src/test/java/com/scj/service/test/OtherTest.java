package com.scj.service.test;

import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Properties;
import java.util.TimeZone;

/**
 * Created by shengcj on 2016/8/8.
 */
public class OtherTest {

    @Test
    public void bigDecimalCanUserNull()
    {
        try {
            String number =null;
            BigDecimal bigDecimal =new BigDecimal("0");
            System.out.println(bigDecimal);
        }catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    public  static <T>  void changeNullToEmpty(T object)
    {
        Class<T> clazz = (Class<T>)object.getClass();

        Field[] fields = clazz.getDeclaredFields();
        for(Field field :fields)
        {
            if(field.getType().equals(String.class))
            {
                field.setAccessible(true);
            }
        }
    }

    @Test
    public void testTurnNull()
    {
        changeNullToEmpty(new Person("134",23));
    }

    class Person
    {
        private String name;
        private Integer age;

        public Person() {
        }

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void testStr()
    {
        String str ="    ";
        System.out.println(str);
    }

    @Test
    public  void testCst()
    {
        System.out.println(TimeZone.getDefault()); //输出当前默认时区
        final TimeZone zone = TimeZone.getTimeZone("GMT+8"); //获取中国时区
        TimeZone.setDefault(zone); //设置时区
        System.out.println(TimeZone.getDefault()); //输出验证
    }

    @Test
    public  void systemProperties() {
        Properties properties = System.getProperties();
        Enumeration e = properties.keys();
        while (e.hasMoreElements())
        {
            String s = (String) e.nextElement();
            String value =System.getProperty(s);
            System.out.println(s+":"+value);
        }
    }

    @Test
    public void testGenerateDDL()
    {

    }

    @Test
    public void testFomartDecimal()
    {
        System.out.println(String.format("%.2f",null));
    }
}
