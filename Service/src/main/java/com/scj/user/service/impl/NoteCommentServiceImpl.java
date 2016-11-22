package com.scj.user.service.impl;

import com.scj.user.entity.Note;
import com.scj.user.entity.NoteComment;
import com.scj.user.entity.User;
import com.scj.user.repository.NoteCommentRepository;
import com.scj.user.service.NoteCommentService;
import com.scj.user.service.NoteService;
import com.scj.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by shengcj on 2016/11/16.
 */
@Service
public class NoteCommentServiceImpl implements NoteCommentService {
    @Resource
    private NoteCommentRepository noteCommentRepository;

    @Resource
    private UserService userService;

    @Resource
    private NoteService noteService;

    @Override
    public List<NoteComment> queryAllNoteComment(Integer noteId) {
        return noteCommentRepository.findByNoteId(noteId);
    }

    @Override
    public void addNoteComment(NoteComment noteComment, Integer userId, Integer noteId) {
        User user = userService.getUserById(userId);
        Note note =noteService.queryNoteById(noteId);
        noteComment.setUser(user);
        noteComment.setNote(note);
        noteComment.setCreateTime(new Date());

        noteCommentRepository.save(noteComment);
    }

    @Override
    public void replyNoteComment(NoteComment noteComment,Integer userId,Integer noteId) {
        User user = userService.getUserById(userId);
        Note note =noteService.queryNoteById(noteId);
        NoteComment targetComment = queryNoteCommentById(noteId);

        noteComment.setTargetComment(targetComment);
        noteComment.setUser(user);
        noteComment.setNote(note);
        noteCommentRepository.save(noteComment);
    }

    @Override
    public NoteComment queryNoteCommentById(Integer id) {
        return noteCommentRepository.findOne(id);
    }

    @Override
    public void deleteNoteComment(Integer id) {
        //// TODO: 2016/11/16 需要测试是否级联删除

    }

    public void setNoteCommentRepository(NoteCommentRepository noteCommentRepository) {
        this.noteCommentRepository = noteCommentRepository;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
