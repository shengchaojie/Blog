package com.scj.common.util;

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
}
