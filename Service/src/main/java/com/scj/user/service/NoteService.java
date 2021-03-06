package com.scj.user.service;

import com.scj.user.entity.Note;
import com.scj.user.entity.NoteTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by shengcj on 2016/9/14.
 */
public interface NoteService {
    Integer addNoteTag(String tagName,Integer userId);
    void deleteNoteTag(Integer tagId);
    void modifyNoteTagName(Integer tagId,String newTagName);

    /**
     * 查询所有标签，用于笔记展示页面，所有人都能看到
     */
    List<NoteTag> queryAllTag();
    List<NoteTag> queryTag(Integer userId);

    void addNote(String title,String content,Integer userId,String tagIds);
    void deleteNote(Integer noteId);

    /**
     * 查询文章，根据标签，没有标签返回空
     * @param tagIds
     * @return
     */
    List<Note> queryNote(List<Integer> tagIds);
    Page<Note> queryNote(List<Integer> tagIds,Pageable pageable);

    List<Note> queryNote(Integer userId,List<Integer> tagIds);

    Note queryNoteById(Integer id);

    List<Note> queryAllNote();

    Page<Note> queryAllNote(Pageable pageable);


}
