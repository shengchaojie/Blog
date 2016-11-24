package com.scj.user.service;

import com.scj.user.entity.NoteComment;

import java.util.List;

/**
 * Created by shengcj on 2016/11/16.
 */
public interface NoteCommentService {
    /**
     * 查询所有评论
     * @param noteId
     * @return
     */
    List<NoteComment> queryAllNoteComment(Integer noteId);

    /**
     * 对文章新增评论
     * @param noteComment
     */
    NoteComment addNoteComment(NoteComment noteComment,Integer userId,Integer noteId);

    /**
     * 回复评论
     */
    List<NoteComment> replyNoteComment(NoteComment noteComment,Integer userId,Integer noteId,Integer targetCommentId);

    /**
     * 根据id获取评论
     * @param id
     * @return
     */
    NoteComment queryNoteCommentById(Integer id);

    /**
     * 删除评论
     */
    void deleteNoteComment(Integer id);
}
