package com.scj.service.test;

import com.scj.user.entity.NoteComment;
import com.scj.user.service.NoteCommentService;
import com.scj.user.service.NoteService;
import com.scj.user.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by shengcj on 2016/9/14.
 * 这边的获取 延迟加载都有问题 不知道如何解决 先把api写好
 */
public class NoteServiceTest extends TestBase {

    @Resource
    private NoteService noteService;

    @Resource
    private UserService userService;

    @Resource
    private NoteCommentService noteCommentService;

    @Test
    public void testAddNoteTag()
    {
        noteService.addNoteTag("标签3",2);
    }

    @Test
    public void testAddNote()
    {
        //noteService.addNote("标题党","标题内容",2,4);
    }

    @Test
    public void testModifyTag()
    {
        noteService.modifyNoteTagName(3,"新标题");
    }

    @Test
    public void testAddNoteComment()
    {
        //User user =userService.getUserByUsername("shengchaojie");
        //Note note =noteService.queryNoteById(5);

        NoteComment targetComment =noteCommentService.queryNoteCommentById(38);

        targetComment.getTargetComment().getContent();
        //targetComment.getChildComment();

        NoteComment noteComment =new NoteComment();
        noteComment.setOrder(1);
        noteComment.setContent("这是一个评论");
        noteComment.setCreateTime(new Date());
        noteComment.setTargetComment(targetComment);
        noteComment.setChildComment(new ArrayList<>());


        noteCommentService.addNoteComment(noteComment,1,5);
    }
}
