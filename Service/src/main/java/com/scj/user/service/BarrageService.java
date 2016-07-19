package com.scj.user.service;

import com.scj.user.entity.Barrage;

import java.util.List;

/**
 * Created by shengcj on 2016/7/19.
 */
public interface BarrageService {
    /**
     * 保存一个弹幕
     * @param barrage
     * @return
     */
    boolean saveBarrage(Barrage barrage);

    /**
     * 获取所有弹幕
     * @return
     */
    List<Barrage> getBarrages();
}
