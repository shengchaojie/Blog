package com.scj.user.service.impl;

import com.scj.user.entity.Barrage;
import com.scj.user.repository.BarrageRepository;
import com.scj.user.service.BarrageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shengcj on 2016/7/19.
 */
@Service
public class BarrageServiceImpl implements BarrageService{
    @Resource
    private BarrageRepository barrageRepository;

    @Override
    public boolean saveBarrage(Barrage barrage) {
        return barrageRepository.save(barrage) != null;
    }

    @Override
    public List<Barrage> getBarrages() {
        return barrageRepository.findAllOrderByCreateTime();
    }
}
