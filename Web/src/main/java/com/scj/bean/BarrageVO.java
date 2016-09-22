package com.scj.bean;

import java.util.Date;

/**
 * Created by shengcj on 2016/7/19.
 */
public class BarrageVO {

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

    //“0”为滚动 “1” 为顶部 “2”为底部
    private String position;

    //保存发弹幕人的姓名
    private String name;

    //弹幕的插入时间
    private Date createTime;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "BarrageVO{" +
                "text='" + text + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", time=" + time +
                '}';
    }
}
