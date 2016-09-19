package com.scj.controller;

import antlr.LexerSharedInputState;
import com.scj.bean.NoteTagVO;
import com.scj.bean.NoteVO;
import com.scj.common.CommonConstants;
import com.scj.common.exception.StatusCode;
import com.scj.common.util.AssertUtils;
import com.scj.common.util.ObjectUtil;
import com.scj.context.ResponseResult;
import com.scj.user.entity.Note;
import com.scj.user.entity.NoteTag;
import com.scj.user.repository.NoteTagRepository;
import com.scj.user.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        //Integer userId =Integer.parseInt((String) session.getAttribute(CommonConstants.SESSION_USER_ID));
        LOGGER.info("user:{},添加标签{}",noteTagName);
        noteService.addNoteTag(noteTagName,2);

        return new ResponseResult<>(1,"OK",true);
    }

    @RequestMapping(value="/noteTag/getAll",method = RequestMethod.GET)
    @ResponseBody
    public List<NoteTagVO> getAllNoteTags( HttpSession session)
    {
        //Integer userId =Integer.parseInt((String) session.getAttribute(CommonConstants.SESSION_USER_ID));

        List<NoteTag> noteTags = noteService.queryTag(2);

        List<NoteTagVO> noteTagVOs =new ArrayList<>();
        noteTags.stream().forEach(noteTag -> noteTagVOs.add(new NoteTagVO(noteTag.getId(),noteTag.getTagName())));

        return noteTagVOs;
    }

    @RequestMapping(value = "/getAll")
    @ResponseBody
    public List<NoteVO> getAllNoteByTags(String tags,HttpSession session)
    {
        String[] tagIds = StringUtils.commaDelimitedListToStringArray(tags);
        List<Integer> tagIntIdS =new ArrayList<>();
        Arrays.stream(tagIds).forEach(t->tagIntIdS.add(Integer.valueOf(t)));
        LOGGER.debug("tags:{},tagIds,{}",tags,tagIds);

        List<Note> notes= noteService.queryNote(2,tagIntIdS);

        List<NoteVO> noteVOs =new ArrayList<>();
        notes.stream().forEach(n->noteVOs.add(
                new NoteVO(n.getTitle(),n.getUser().getNickname(),n.getCreateTime().toString(),n.getNoteTags())));

        return noteVOs;

    }
}
