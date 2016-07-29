package com.scj.test;

import com.scj.bean.BarrageVO;
import com.scj.common.util.CollectionUtils;
import com.scj.common.util.ObjectUtil;
import com.scj.user.entity.Barrage;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shengchaojie on 2016/7/19.
 */

public class UtilTest {

    @Test
    @Ignore
    public void testObjectUtil()
    {
        Barrage barrage =new Barrage();
        barrage.setText("123");
        barrage.setColor("white");
        barrage.setPosition("1");
        barrage.setSize("1");
        barrage.setTime(4);

        BarrageVO barrage1 = ObjectUtil.convertTo(barrage,BarrageVO.class);

        System.out.println(barrage1);
    }

    @Test
    public void testCollectionConvertTo()
    {
        List<Object> list =new ArrayList<>();

        Barrage barrage =new Barrage();
        barrage.setText("1234");
        barrage.setColor("white");
        barrage.setPosition("1");
        barrage.setSize("1");
        barrage.setTime(4);

        Barrage barrage2 =new Barrage();
        barrage2.setText("123");
        barrage2.setColor("white");
        barrage2.setPosition("1");
        barrage2.setSize("1");
        barrage2.setTime(4);

        list.add(barrage);
        list.add(barrage2);


        CollectionUtils.isEmpty(list);
        //CollectionUtils.convertTo(list,BarrageVO.class);
        List<BarrageVO> barrageVOlist= ObjectUtil.convertTo(list,BarrageVO.class);

        System.out.println(barrageVOlist);
    }

}
