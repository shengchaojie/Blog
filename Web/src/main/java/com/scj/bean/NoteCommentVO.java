package com.scj.bean;

import java.util.Date;

/**
 * Created by shengcj on 2016/11/23.
 */
public class NoteCommentVO {
    private String content;
    private Date createTime;
    private Integer targetCommentId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTargetCommentId() {
        return targetCommentId;
    }

    public void setTargetCommentId(Integer targetCommentId) {
        this.targetCommentId = targetCommentId;
    }
}
