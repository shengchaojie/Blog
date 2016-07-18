package com.scj.common.util;

import com.scj.common.exception.BusinessException;
import com.scj.common.exception.StatusCode;
import javafx.beans.binding.ObjectExpression;

/**
 * Created by Administrator on 2016/7/11.
 */
public class AssertUtils {

    /**
     * 判断string是否为空,为空抛出错误
     * @param text
     * @param statusCode
     */
    public static void isStringEmpty(String text, StatusCode statusCode)
    {
        if(StringUtils.isEmpty(text))
        {
            throwStatusExceptionIfNotNull(statusCode);
        }
    }

    public static void isStringEmpty(String text){
        isStringEmpty(text,null);
    }

    public static void isIntegerInRange(int integer,int start,int end,StatusCode statusCode)
    {
        if(integer<start||integer>end)
        {
            throwStatusExceptionIfNotNull(statusCode);
        }
    }

    public static void throwStatusExceptionIfNotNull(StatusCode statusCode)
    {
        if(statusCode!=null) {
            throw new BusinessException(statusCode);
        }else
        {
            throw new IllegalArgumentException();
        }
    }
}
