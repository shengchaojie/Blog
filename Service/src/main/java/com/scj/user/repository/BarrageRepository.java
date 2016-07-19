package com.scj.user.repository;

import com.scj.user.entity.Barrage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by shengcj on 2016/7/19.
 */
public interface BarrageRepository extends JpaRepository<Barrage,Integer> {
}
