package com.scj.user.service.impl;

import com.scj.user.entity.Talk;
import com.scj.user.repository.TalkRepository;
import com.scj.user.service.TalkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shengchaojie on 2016/7/17.
 */

@Service
public class TalkServiceImpl implements TalkService{

    @Resource
    private TalkRepository talkRepository;

    @Override
    public void addTalk(Talk talk) {
        talkRepository.save(talk);

    }

    @Override
    public void deleteTalk(Integer talkId) {
        talkRepository.delete(talkId);
    }

    @Override
    public List<Talk> getTalks(Integer userId) {
        return talkRepository.findByUserId(userId);
    }
}
