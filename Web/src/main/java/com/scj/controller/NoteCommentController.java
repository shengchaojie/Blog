package com.scj.controller;

import com.scj.bean.NoteCommentVO;
import com.scj.common.CommonConstants;
import com.scj.common.exception.BusinessException;
import com.scj.common.exception.StatusCode;
import com.scj.context.ResponseResult;
import com.scj.user.entity.NoteComment;
import com.scj.user.service.NoteCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by shengcj on 2016/11/16.
 */

@Controller
@RequestMapping("/noteComment")
public class NoteCommentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoteCommentController.class);

    @Resource
    private NoteCommentService noteCommentService;

    @RequestMapping("/getAll/{noteId}")
    @ResponseBody
    public ResponseResult<List<NoteComment>> getAll(@PathVariable("noteId") Integer noteId)
    {
        return new ResponseResult<>(StatusCode.OK,noteCommentService.queryAllNoteComment(noteId));
    }

    @RequestMapping( "/add/{noteId}")
    @ResponseBody
    public ResponseResult<NoteComment> addNoteComment(@PathVariable("noteId") String noteId, NoteCommentVO noteCommentVO, HttpSession session)
    {
        Integer userId =(Integer) session.getAttribute(CommonConstants.USER_ID);
        if(userId==null)
        {
            throw new BusinessException(StatusCode.USER_NOT_LOGIN);
        }

        NoteComment noteComment =new NoteComment();
        noteComment.setContent(noteCommentVO.getContent());
        noteComment.setCreateTime(new Date());

        return  new ResponseResult<>(StatusCode.OK,noteCommentService.addNoteComment(noteComment,userId,Integer.parseInt(noteId)));
    }

    @RequestMapping("/reply/{noteId}")
    @ResponseBody
    public ResponseResult<List<NoteComment>> replyNoteComment(NoteCommentVO noteCommentVO, @PathVariable("noteId") Integer noteId, HttpSession session)
    {
        Integer userId =(Integer) session.getAttribute(CommonConstants.USER_ID);
        if(userId==null)
        {
            throw new BusinessException(StatusCode.USER_NOT_LOGIN);
        }
        NoteComment noteComment =new NoteComment();
        noteComment.setContent(noteCommentVO.getContent());
        noteComment.setCreateTime(new Date());

        return  new ResponseResult<>(StatusCode.OK,noteCommentService.replyNoteComment(noteComment,userId,noteId,noteCommentVO.getTargetCommentId()));
    }
}
