package com.scj.user.service;

import com.scj.user.entity.Note;
import com.scj.user.entity.NoteTag;

import java.util.List;

/**
 * Created by shengcj on 2016/9/14.
 */
public interface NoteService {
    void addNoteTag(String tagName,Integer userId);
    void deleteNoteTag(Integer tagId);
    void modifyNoteTagName(Integer tagId,String newTagName);
    List<NoteTag> queryTag(Integer userId);

    void addNote(String title,String content,Integer userId,Integer tagId);
    void deleteNote(Integer noteId);
    List<Note> queryNote(Integer userId);
}
