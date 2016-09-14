package com.scj.service.test;

import com.scj.user.repository.NoteRepository;
import com.scj.user.repository.NoteTagRepository;
import com.scj.user.service.NoteService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by shengcj on 2016/9/14.
 */
public class NoteServiceTest extends TestBase {

    @Resource
    private NoteService noteService;

    @Test
    public void testAddNoteTag()
    {
       // noteService.addNoteTag("123",1);
        System.out.println(1);
    }

}
