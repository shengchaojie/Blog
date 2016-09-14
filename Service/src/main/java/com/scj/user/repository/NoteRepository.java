package com.scj.user.repository;

import com.scj.user.entity.Note;
import com.scj.user.entity.NoteTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by shengcj on 2016/9/1.
 */
public interface NoteRepository extends JpaRepository<Note,Integer>{
    //List<Note> findByUserIdAndTagId(int userId,int tagId);

    List<Note> findByUserId(int userId);
}
