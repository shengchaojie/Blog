package com.scj.service.test;

/**
 * Created by shengchaojie on 2016/7/30.
 * 类加载器先初始化变量 然后按照代码顺序进行赋值
 */
public class ClassTest {

    static {
        b=5;
        System.out.println("------1");
    }

    public static int a =1;
    public static int b =2;
    public static int c =3;

    static {
        c=6;
        System.out.println("*******2");
    }

    public static void main(String[] args) {
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
