package com.scj.controller;

import com.scj.common.CommonConstants;
import com.scj.common.exception.StatusCode;
import com.scj.common.util.AssertUtils;
import com.scj.context.ResponseResult;
import com.scj.user.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by shengcj on 2016/9/18.
 */
@Controller
@RequestMapping("/note")
public class NoteController {

    public static final Logger LOGGER = LoggerFactory.getLogger(NoteController.class);

    @Resource
    private NoteService  noteService;

    @RequestMapping("/noteTag/add/{name}")
    @ResponseBody
    public ResponseResult<Boolean> addNoteTag(@PathVariable("name")String noteTagName, HttpSession session)
    {
        AssertUtils.isStringEmpty(noteTagName);

        Integer userId =Integer.parseInt((String) session.getAttribute(CommonConstants.SESSION_USER_ID));
        LOGGER.info("user:{},添加标签{}",noteTagName);
        noteService.addNoteTag(noteTagName,userId);

        return new ResponseResult<Boolean>(StatusCode.OK,true);
    }
}
