package com.scj.user.service;

import com.scj.user.entity.Talk;

import java.util.List;

/**
 * Created by shengchaojie on 2016/7/17.
 */
public interface TalkService {

    void addTalk(Talk talk);

    void deleteTalk(Integer id);

    List<Talk> getTalks(Integer userId);
}
