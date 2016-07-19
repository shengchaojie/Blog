package com.scj.common.util;

import java.lang.reflect.Field;

/**
 * Created by shengcj on 2016/7/19.
 */
public class ObjectUtil {
    public static <T, S> T convertTo(S source, Class<T> clazz) {
        Field[] sourceFields = source.getClass().getFields();

        T instance = null;
        try {
            instance = clazz.newInstance();
            for(Field field:sourceFields)
            {
                // TODO: 2016/7/19 完成反射方法书写 
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    
        return null;
    }
}
