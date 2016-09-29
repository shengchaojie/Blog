package com.scj.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shengcj on 2016/9/1.
 */
@Entity
@Table(name = "note_tag")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler","notes","user"})
public class NoteTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Integer id;

    @Column(name = "tag_name")
    private String tagName;

    @ManyToOne()
    @JoinColumn(name="user_id",nullable = false)
    private User user ;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name="update_time",columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    @ManyToMany(mappedBy = "noteTags")
    private Set<Note> notes =new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
