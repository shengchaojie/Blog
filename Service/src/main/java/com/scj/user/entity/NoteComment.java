package com.scj.user.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by shengcj on 2016/9/1.
 * 先把帖子功能完成 这个后期增加
 */
public class NoteComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Integer id;

    private String content;

    @Column(name="create_time")
    private Date createTime;

    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user =new User();

    @ManyToOne()
    @JoinColumn(name = "note_id")
    private Note note =new Note();

    @ManyToOne
    @JoinColumn(name = "target_comment_id")
    private NoteComment targetComment =new NoteComment();
}
