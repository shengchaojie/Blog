package com.scj.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shengcj on 2016/9/1.
 * 先把帖子功能完成 这个后期增加
 */
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler","note","childComment"})
public class NoteComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Integer id;

    private String content;

    @Column(name = "corder")
    private Integer order;

    @Column(name="create_time")
    private Date createTime;

    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user =new User();

    @ManyToOne()
    @JoinColumn(name = "note_id")
    private Note note =new Note();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid")
    private NoteComment targetComment;

    @OneToMany(targetEntity = NoteComment.class,cascade = {CascadeType.ALL},mappedBy = "targetComment",fetch =FetchType.LAZY )
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("corder")
    private List<NoteComment> childComment =new ArrayList<>();

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public NoteComment getTargetComment() {
        return targetComment;
    }

    public void setTargetComment(NoteComment targetComment) {
        this.targetComment = targetComment;
    }

    public List<NoteComment> getChildComment() {
        return childComment;
    }

    public void setChildComment(List<NoteComment> childComment) {
        this.childComment = childComment;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
