package com.scj.controller;

import com.scj.bean.NoteTagVO;
import com.scj.bean.NoteVO;
import com.scj.common.util.AssertUtils;
import com.scj.context.ResponseResult;
import com.scj.user.entity.Note;
import com.scj.user.entity.NoteTag;
import com.scj.user.service.NoteService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by shengcj on 2016/9/18.
 */
@Controller
@RequestMapping("/note")
public class NoteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoteController.class);

    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[] { ".bmp", ".jpg", ".jpeg", ".gif", ".png" };

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

    @RequestMapping("/add")
    @ResponseBody
    public String addNote(NoteVO noteVO)
    {
        noteService.addNote(noteVO.getTitle(),noteVO.getContent(),2,noteVO.getTagId());

        return "/note";
    }

    @RequestMapping(value="/noteTag/getAll",method = RequestMethod.GET)
    @ResponseBody
    public List<NoteTagVO> getAllNoteTags( HttpSession session)
    {
        List<NoteTag> noteTags = noteService.queryAllTag();

        List<NoteTagVO> noteTagVOs =new ArrayList<>();
        noteTags.stream().forEach(noteTag -> noteTagVOs.add(new NoteTagVO(noteTag.getId(),noteTag.getTagName())));

        return noteTagVOs;
    }

    @RequestMapping(value = "/content/get/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getNoteContentById(@PathVariable("id")Integer noteId)
    {
        Note note =noteService.queryNoteById(noteId);

        return note.getContent();
    }

    @RequestMapping(value = "/getByTagIds")
    @ResponseBody
    public List<NoteVO> getAllNoteByTags(String tags,HttpSession session)
    {
        // TODO: 2016/9/23 这边代码太挫 
        String[] tagIds = StringUtils.split(tags,",");
        List<Integer> tagIntIdS =new ArrayList<>();
        Arrays.stream(tagIds).forEach(t->tagIntIdS.add(Integer.valueOf(t)));
        LOGGER.debug("tags:{},tagIds,{}",tags,tagIds);

        List<Note> notes= noteService.queryNote(tagIntIdS);

        List<NoteVO> noteVOs =new ArrayList<>();
        notes.stream().forEach(n->noteVOs.add(
                new NoteVO(n.getId(),n.getTitle(),n.getUser().getNickname(),n.getCreateTime().toString(),n.getNoteTags())));

        return noteVOs;
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ResponseBody
    public  List<NoteVO> getAllNote()
    {
        List<Note> notes= noteService.queryAllNote();
        List<NoteVO> noteVOs =new ArrayList<>();
        notes.stream().forEach(n->noteVOs.add(
                new NoteVO(n.getId(),n.getTitle(),n.getUser().getNickname(),n.getCreateTime().toString(),n.getNoteTags())));

        return noteVOs;

    }

    @RequestMapping(value = "upload")
    @ResponseBody
    public String uploadImg(@RequestParam("myFileName") MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isLegal =false;
        for(String type :IMAGE_TYPE)
        {
            if(StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(),type))
            {
                isLegal =true;
                break;
            }
        }

        if(!isLegal)
        {
            return "";
        }

        String webRootPath = request.getServletContext().getRealPath("/");
        LOGGER.debug("服务器根路径:{}",webRootPath);
        String filePath =this.getFilePath(webRootPath,uploadFile.getOriginalFilename());

        File file =new File(filePath);
        uploadFile.transferTo(file);

        String picUrl =StringUtils.replace(StringUtils.substringAfter(filePath,webRootPath),"\\","/");

        return picUrl;
    }

    private String getFilePath(String webRootPath ,String sourceFileName)
    {
        String baseFolder =webRootPath +"uploadImages";
        DateTime now =new DateTime(new Date());
        String fileFolder =baseFolder+ File.separator+now.toString("yyyy")
                +File.separator+now.toString("MM")+File.separator+now.toString("dd");

        LOGGER.debug("上传文件地址:{}",fileFolder);

        File file =new File(fileFolder);
        if(!file.isDirectory())
        {
            file.mkdirs();
        }
        String fileName =now.toString("yyyyMMddhhmmssSSSS") + RandomUtils.nextInt(100, 9999) + "." + StringUtils.substringAfterLast(sourceFileName, ".");
        LOGGER.debug("上传文件名:{}",fileName);
        return fileFolder + File.separator + fileName;
    }

}
