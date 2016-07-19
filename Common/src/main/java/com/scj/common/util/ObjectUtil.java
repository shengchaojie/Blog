package com.scj.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by shengcj on 2016/7/19.
 */
public class ObjectUtil {
    // TODO: 2016/7/19 可能做一些优化
    public static <T, S> T convertTo(S source, Class<T> clazz) {
        //Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields =clazz.getDeclaredFields();
        Method[] sourceMethods =source.getClass().getDeclaredMethods();
        Method[] targetMethods =clazz.getDeclaredMethods();

        T instance = null;
        try {
            instance = clazz.newInstance();

            for(Field targetField:targetFields)//循环目标对象的属性，逐一对其赋值
            {
                String fieldName =targetField.getName();
                /*Field sourceField =null;
                if((sourceField=getField(sourceFields,fieldName))!=null)
                {
                    Object value =sourceField.get(source);
                    targetField.set(instance,value);
                }*/
                //需要拿到source对象的get方法，和target对象的set方法
                setField(sourceMethods,targetMethods,fieldName,source,instance);
            }

            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    
        return null;
    }

    private static Field getField(Field[] fields,String name)
    {
        for(Field field:fields)
        {
            if(field.getName().equalsIgnoreCase(name))
            {
                return field;
            }
        }
        return null;
    }

    private static void setField(Method[] sourceMethods,Method[] targetMethods,String fieldName,Object source,Object target)
    {
        //String upperFieldName =Character.toUpperCase(fieldName.charAt(0))+fieldName.substring(1);
        String setterName ="set"+fieldName;
        String getterName ="get"+fieldName;

        Method sourceMethod =getMethod(sourceMethods,getterName);
        Method targetMethod =getMethod(targetMethods,setterName);

        if(sourceMethod==null||targetMethod==null)
        {
            return ;
        }


        try {
            Object sourceValue =sourceMethod.invoke(source,null);
            targetMethod.invoke(target,sourceValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private static Method getMethod(Method[] methods,String fieldName)
    {
        for(Method method:methods)
        {
            if(method.getName().equalsIgnoreCase(fieldName))
            {
                return method;
            }
        }
        return null;
    }

    public static <T,S> List<T> convertTo(List<S> objects, Class<T> clazz)
    {

        List<T> list =new ArrayList<>();

        for(S object:objects)
        {
            T targetObject =ObjectUtil.convertTo(object,clazz);
            list.add(targetObject);
        }

        return list;

    }
}
