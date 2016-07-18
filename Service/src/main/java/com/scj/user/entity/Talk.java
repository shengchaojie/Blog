package com.scj.user.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by shengchaojie on 2016/7/17.
 */
@Entity
@Table(name = "talk")
public class Talk {
    @Id
    private Integer id;

    private String content;

    private String place;

    private Integer views;

    private Integer likes;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
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

    @Override
    public String toString() {
        return "Talk{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", place='" + place + '\'' +
                ", views=" + views +
                ", likes=" + likes +
                ", user=" + user.getUsername() +
                ", createTime=" + createTime +
                '}';
    }
}
