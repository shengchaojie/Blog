package com.scj.service.test;

import com.scj.user.repository.NoteRepository;
import com.scj.user.repository.NoteTagRepository;
import com.scj.user.service.NoteService;
import com.scj.user.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by shengcj on 2016/9/14.
 * 这边的获取 延迟加载都有问题 不知道如何解决 先把api写好
 */
public class NoteServiceTest extends TestBase {

    @Resource
    private NoteService noteService;

    @Resource
    private UserService userService;



    @Test
    public void testAddNoteTag()
    {
        noteService.addNoteTag("标签3",2);
    }

    @Test
    public void testAddNote()
    {
        noteService.addNote("标题党","标题内容",2,4);
    }

    @Test
    public void testModifyTag()
    {
        noteService.modifyNoteTagName(3,"新标题");
    }
}
