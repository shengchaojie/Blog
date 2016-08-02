package com.scj.user.repository;

import com.scj.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by shengcj on 2016/8/2.
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer>{
}
