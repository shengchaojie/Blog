package com.scj.user.service.impl;

import com.scj.common.exception.BusinessException;
import com.scj.common.exception.StatusCode;
import com.scj.common.util.StringUtils;
import com.scj.user.entity.Note;
import com.scj.user.entity.NoteTag;
import com.scj.user.entity.User;
import com.scj.user.repository.NoteRepository;
import com.scj.user.repository.NoteTagRepository;
import com.scj.user.repository.UserRepository;
import com.scj.user.service.NoteService;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by shengcj on 2016/9/14.
 */
@Service
public class NoteServiceImpl implements NoteService{
    private static final Logger LOGGER = LoggerFactory.getLogger(NoteServiceImpl.class);

    @Resource
    private NoteRepository noteRepository;

    @Resource
    private NoteTagRepository noteTagRepository;

    @Resource
    private UserRepository userRepository;

    @Override
    public void addNoteTag(String tagName, Integer userId) {
        User user =userRepository.getOne(userId);
        if(user==null)
        {
            LOGGER.info("该用户不存在 userId={}",userId);
            throw new BusinessException(StatusCode.USER_NOT_EXISTED);
        }

        NoteTag noteTag =new NoteTag();
        noteTag.setTagName(tagName);
        noteTag.setUser(user);
        noteTag.setCreateTime(new Date());
        noteTagRepository.save(noteTag);

    }

    @Override
    public void deleteNoteTag(Integer tagId) {
        noteTagRepository.delete(tagId);
    }

    @Override
    public void modifyNoteTagName(Integer tagId, String newTagName) {
        NoteTag noteTag =noteTagRepository.getOne(tagId);
        if(noteTag==null)
        {
            LOGGER.info("该标签不存在 tagId={}",tagId);
            throw new BusinessException(StatusCode.NOTE_TAG_NOT_EXISTED);
        }
        //Hibernate.initialize(noteTag);
        //System.out.println(noteTag.getTagName());

        noteTag.setTagName(newTagName);
        noteTagRepository.save(noteTag);

    }

    @Override
    public List<NoteTag> queryTag(Integer userId) {
        return noteTagRepository.findByUserId(userId);
    }

    @Override
    public void addNote(String title, String content, Integer userId, Integer tagId) {
        User user =userRepository.findOne(userId);
        if(user==null)
        {
            LOGGER.info("该用户不存在 userId={}",userId);
            throw new BusinessException(StatusCode.USER_NOT_EXISTED);
        }

        Set<NoteTag> noteTags =new HashSet<>();
        NoteTag noteTag =noteTagRepository.getOne(tagId);
        if(noteTag==null)
        {
            LOGGER.info("该标签不存在 tagId={}",tagId);
            throw new BusinessException(StatusCode.NOTE_TAG_NOT_EXISTED);
        }
        noteTags.add(noteTag);

        Note note =new Note();
        note.setContent(content);
        note.setTitle(title);
        note.setUser(user);
        note.setNoteTags(noteTags);

        noteRepository.save(note);
    }

    @Override
    public void deleteNote(Integer noteId) {
        noteRepository.delete(noteId);
    }

    @Override
    public List<Note> queryNote(Integer userId,List<Integer> tagIds) {
        if(tagIds==null||tagIds.size()==0)
        {
            return noteRepository.findByUserId(userId);
            //return new ArrayList<>();
        }else {
            return noteTagRepository.findByUserIdAndTagIds(userId,tagIds);
        }


    }
}
