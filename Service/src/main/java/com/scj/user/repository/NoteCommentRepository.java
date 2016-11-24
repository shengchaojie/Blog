package com.scj.user.repository;

import com.scj.user.entity.NoteComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by shengcj on 2016/11/1.
 */
public interface NoteCommentRepository  extends JpaRepository<NoteComment,Integer>{

    @Query("from NoteComment n where n.note.id =:noteId order by n.createTime desc")
    List<NoteComment> findByNoteId(@Param("noteId") Integer noteId);

}
