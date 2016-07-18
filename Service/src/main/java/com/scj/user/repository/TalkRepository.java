package com.scj.user.repository;

import com.scj.user.entity.Talk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by shengchaojie on 2016/7/17.
 */
public interface TalkRepository extends JpaRepository<Talk,Integer>{
    List<Talk> findByUserId(Integer userId);
}
