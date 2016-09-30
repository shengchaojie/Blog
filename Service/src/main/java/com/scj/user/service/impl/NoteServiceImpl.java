package com.scj.user.service.impl;

import com.scj.common.exception.BusinessException;
import com.scj.common.exception.StatusCode;
import com.scj.user.entity.Note;
import com.scj.user.entity.NoteTag;
import com.scj.user.entity.User;
import com.scj.user.repository.NoteRepository;
import com.scj.user.repository.NoteTagRepository;
import com.scj.user.repository.UserRepository;
import com.scj.user.service.NoteService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Integer addNoteTag(String tagName, Integer userId) {
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
        return  noteTagRepository.save(noteTag).getId();
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
    public List<NoteTag> queryAllTag() {
        return noteTagRepository.findAll();
    }

    @Override
    public List<NoteTag> queryTag(Integer userId) {
        return noteTagRepository.findByUserId(userId);
    }

    @Override
    public void addNote(String title, String content, Integer userId, String tagIds) {
        User user =userRepository.findOne(userId);
        if(user==null)
        {
            LOGGER.info("该用户不存在 userId={}",userId);
            throw new BusinessException(StatusCode.USER_NOT_EXISTED);
        }
        String[] tags = StringUtils.splitByWholeSeparator(tagIds,",");
        Set<NoteTag> noteTags =new HashSet<>();
        for(String tagId :tags) {
            NoteTag noteTag = noteTagRepository.getOne(Integer.parseInt(tagId));
            if (noteTag == null) {
                LOGGER.info("该标签不存在 tagId={}", tagId);
                throw new BusinessException(StatusCode.NOTE_TAG_NOT_EXISTED);
            }
            noteTags.add(noteTag);
        }
        Note note =new Note();
        note.setContent(content);
        note.setTitle(title);
        note.setUser(user);
        note.setNoteTags(noteTags);
        note.setCreateTime(new Date());
        note.setUpdateTime(new Date());

        noteRepository.save(note);
    }

    @Override
    public void deleteNote(Integer noteId) {
        noteRepository.delete(noteId);
    }

    @Override
    public List<Note> queryNote(List<Integer> tagIds) {
        if(tagIds==null||tagIds.size()==0)
        {
            //return noteRepository.findAll();
            return new ArrayList<>();
        }else {
            return noteTagRepository.findByTagIds(tagIds);
        }
    }

    @Override
    public Page<Note> queryNote(List<Integer> tagIds, Pageable pageable) {
        if(tagIds==null||tagIds.size()==0)
        {
            return new PageImpl<Note>(new ArrayList<>());
        }else {
            return noteTagRepository.findByTagIds(tagIds,pageable);
        }
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

    @Override
    public Note queryNoteById(Integer id) {
        return noteRepository.findOne(id);
    }

    @Override
    public List<Note> queryAllNote() {
         return noteRepository.findAll();
    }

    @Override
    public Page<Note> queryAllNote(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }
}
