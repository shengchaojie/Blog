package com.scj.service.test;

import org.junit.Test;

import javax.print.DocFlavor;
import java.lang.reflect.Field;
import java.math.BigDecimal;

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

}
