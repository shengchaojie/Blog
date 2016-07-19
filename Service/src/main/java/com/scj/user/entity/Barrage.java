package com.scj.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by shengcj on 2016/7/19.
 * 弹幕的实体类，目前做成匿名的，后期可以改成实名发弹幕
 */
@Entity
@Table(name = "barrage")
public class Barrage {
    @Id
    private  Integer id;

    //弹幕内容
    private String text;

    //弹幕颜色
    private String color;

    //“0”为小字 ”1”为大字
    private String size;

    //弹幕刷的相对时间
    //我现在弹幕的作用是个人博客相当于留言功能,所以时间存一份就行
    // 从0开始，不是实时弹幕 要实时可以用websocket 或者openfire
    private Integer time;
}
