package com.scj.bean;

import java.util.Date;

/**
 * Created by shengcj on 2016/9/22.
 */
public class BarrageCommentVO {
    //保存发弹幕人的姓名
    private String name;

    //弹幕的插入时间
    private Date createTime;

    //弹幕内容
    private String text;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
