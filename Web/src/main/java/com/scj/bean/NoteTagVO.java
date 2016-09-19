package com.scj.bean;

/**
 * Created by shengcj on 2016/9/18.
 */
public class NoteTagVO {

    private Integer tagId;
    private String tagName;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public NoteTagVO() {
    }

    public NoteTagVO(Integer tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }
}
