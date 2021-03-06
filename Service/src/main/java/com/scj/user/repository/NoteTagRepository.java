package com.scj.user.repository;

import com.scj.user.entity.Note;
import com.scj.user.entity.NoteTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by shengcj on 2016/9/1.
 */
public interface NoteTagRepository extends JpaRepository<NoteTag,Integer>{
    @Query("from NoteTag a where a.user.id =:id")
    List<NoteTag> findByUserId(@Param("id") int userId);

    @Query("from Note as note left join  note.noteTags as noteTag where note.user.id =:id  and noteTag.id in (:tagIds)")
    List<Note>  findByUserIdAndTagIds(@Param("id") int userId,@Param("tagIds")List<Integer> tagIds);

    @Query("select distinct note from Note as note left join  note.noteTags as noteTag where  noteTag.id in (:tagIds)")
    List<Note> findByTagIds(@Param("tagIds")List<Integer> tagIds);

    @Query("select distinct note from Note as note left join  note.noteTags as noteTag where  noteTag.id in (:tagIds)")
    Page<Note> findByTagIds(@Param("tagIds")List<Integer> tagIds, Pageable pageable);
}
