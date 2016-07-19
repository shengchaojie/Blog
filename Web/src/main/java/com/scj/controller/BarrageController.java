package com.scj.controller;

import com.bean.BarrageVO;
import com.scj.user.entity.Barrage;
import com.scj.user.service.BarrageService;
import com.scj.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shengcj on 2016/7/19.
 */
@Controller
@RequestMapping("/barrage")
public class BarrageController {
    @Resource
    private BarrageService barrageService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public BarrageVO testJson() {
        BarrageVO barrageVO = new BarrageVO();
        barrageVO.setColor("white");
        barrageVO.setText("123");
        barrageVO.setSize("1");
        barrageVO.setTime(10);

        return barrageVO;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String saveBarrage(BarrageVO barrageVO) {
        Barrage barrage = new Barrage();
        // TODO: 2016/7/19 需要写个反射类
        barrage.setColor(barrageVO.getColor());
        barrage.setSize(barrageVO.getSize());
        barrage.setText(barrageVO.getText());
        barrage.setTime(barrageVO.getTime());

        if (barrageService.saveBarrage(barrage)) {
            return "({\"code\":\"0\",\"status\":\"success\"})";
        }

        return "({\"code\":\"1\",\"status\":\"failed\"})";
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ResponseBody
    public List<Barrage> getAllBarrages()
    {
        return barrageService.getBarrages();
    }
}
