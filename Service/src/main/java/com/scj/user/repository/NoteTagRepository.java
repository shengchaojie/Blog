package com.scj.user.repository;

import com.scj.user.entity.NoteTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by shengcj on 2016/9/1.
 */
public interface NoteTagRepository extends JpaRepository<NoteTag,Integer>{
    List<NoteTag> findByUserId(int userId);
}
