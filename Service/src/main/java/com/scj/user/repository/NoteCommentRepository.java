package com.scj.user.repository;

import com.scj.user.entity.NoteComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by shengcj on 2016/11/1.
 */
public interface NoteCommentRepository  extends JpaRepository<NoteComment,Integer>{

    List<NoteComment> findByNoteId(Integer noteId);

}
