package com.scj.user.repository;

import com.scj.user.entity.Barrage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by shengcj on 2016/7/19.
 */
public interface BarrageRepository extends JpaRepository<Barrage,Integer> {
    @Query("from Barrage b order by b.createTime desc")
    List<Barrage> findAllOrderByCreateTime();
}
