package com.scj.user.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by shengcj on 2016/9/1.
 */
@Entity
@Table(name = "note_tag")
public class NoteTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Integer id;

    @Column(name = "tag_name")
    private String tagName;

    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name="user_id",nullable = false)
    private User user =new User();

    @Column(name = "create_time")
    private Date createTime;

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
}
