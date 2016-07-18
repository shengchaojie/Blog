package com.scj.user.repository;

import com.scj.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by shengchaojie on 2016/7/17.
 */
public interface UserRepository extends JpaRepository<User,Integer>  {

    List<User> findByUsername(String username);

    User findByUsernameAndPassword(String username,String password);

    @Query("select t from User t where t.nickname = :nickname")
    List<User> findByNickname(@Param("nickname")String nickname);

}
