package com.scj.bean;

import com.scj.user.entity.NoteTag;

import java.util.Set;

/**
 * Created by shengchaojie on 2016/9/19.
 */
public class NoteVO {
    private String title;
    private String author;
    private String createTime;
    private String tagId;

    public NoteVO() {
    }

    public NoteVO(String title, String author, String createTime, String tagId) {
        this.title = title;
        this.author = author;
        this.createTime = createTime;
        this.tagId = tagId;
    }

    public NoteVO(String title, String author, String createTime, Set<NoteTag> noteTagSet)
    {
        StringBuilder sb =new StringBuilder();
        noteTagSet.stream().forEach(t->sb.append(t.getId()).append(","));
        sb.deleteCharAt(sb.length()-1);
        this.title = title;
        this.author = author;
        this.createTime = createTime;
        this.tagId = sb.toString();

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
