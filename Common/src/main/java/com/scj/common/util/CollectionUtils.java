package com.scj.common.util;

import com.sun.corba.se.spi.ior.ObjectKey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/11.
 */
public class CollectionUtils {

    public static boolean isEmpty(Collection collection)
    {
        return collection ==null ||collection.size()==0;
    }

    public static boolean isEmpty(Map map)
    {
        return map==null||map.size()==0;
    }

    public static boolean isEmpty(Object[] objects)
    {
        return objects==null||objects.length==0;
    }

    public static  boolean contains(Collection collection ,Object object)
    {
        if(object==null)
        {
            return false;
        }
        for(Object element:collection)
        {
            if(element.getClass().equals(object.getClass())&&object.equals(element))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(Object[] objects,Object o)
    {
        if(o==null)
        {
            return false;
        }

        for (Object object:objects)
        {
            if(o.getClass().equals(object.getClass())&&object.equals(o))
            {
                return true;
            }
        }

        return false;
    }


    /**
     * 这个方法是什么黑洞？
     * @param objects
     * @param clazz
     * @param <T>
     * @return
     */
    @Deprecated
    public static <T> List convertTo(List<Object> objects,Class<T> clazz)
    {

        List<T> list =new ArrayList<>();

        for(Object object:objects)
        {
            T targetObject =ObjectUtil.convertTo(object,clazz);
            list.add(targetObject);
        }

        return list;

    }


}
