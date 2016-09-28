package com.scj.bean;

import com.scj.user.entity.NoteTag;

import java.util.Set;

/**
 * Created by shengchaojie on 2016/9/19.
 */
public class NoteVO {
    private Integer id;
    private String title;
    private String author;
    private String createTime;
    private String tagId;
    private String content;

    public NoteVO() {
    }

    public NoteVO(Integer id,String title, String author, String createTime, String tagId) {
        this.id=id;
        this.title = title;
        this.author = author;
        this.createTime = createTime;
        this.tagId = tagId;
    }

    public NoteVO(Integer id,String title, String author, String createTime, Set<NoteTag> noteTagSet)
    {
        StringBuilder sb =new StringBuilder();
        noteTagSet.stream().forEach(t->sb.append(t.getId()).append(","));
        if(sb.length()>0) {//可能有些文章不存在标签
            sb.deleteCharAt(sb.length() - 1);
        }
        this.id=id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NoteVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", createTime='" + createTime + '\'' +
                ", tagId='" + tagId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
