package com.scj.interceptor;

import ch.qos.logback.classic.util.LoggerNameUtil;
import javafx.collections.transformation.SortedList;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.scj.interceptor.FrequencyControlLevel.*;

/**
 * Created by shengchaojie on 2016/8/3.
 */
public abstract class FrequencyInterceptor implements HandlerInterceptor{

    private static final Logger LOGGER = LoggerFactory.getLogger(FrequencyInterceptor.class);

    //url 和接口信息的映射
    private static final Map<String,List<InterfaceInfo>> interfaceInvokeMap =new ConcurrentHashMap<>();

    //保存元数据 初始化时加载应该不存在线程安全问题
    private static final Map<String,FrequencyMetaData> metaDataMap =new HashMap<>();

    //加载元数据
    static
    {
        LOGGER.info("加载FrequencyInterceptor配置信息");
        readXmlConfig();
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String url =httpServletRequest.getRequestURI();
        String context =httpServletRequest.getContextPath();//没有上下文根的时候 为空
        if(!"".equals(context))
        {
            url =url.substring(url.indexOf("/",1));
        }
        String ipAddress =httpServletRequest.getRemoteAddr();

        FrequencyMetaData metaData =metaDataMap.get(url);
        //如果元数据不存在，补监控
        if(metaData==null)
        {
            LOGGER.debug("{} 不存在配置的元数据",url);
            return true;
        }

        //拿到历史调用信息，如何做缓存？
        //刷新频率太大  也没必要做缓存
        List<InterfaceInfo> historyInvoke =this.getInterfaceInfos(
                url,
                metaData.getLevel(),
                metaData.getLevel().equals(username)?
                        getUserName(httpServletRequest):
                        metaData.getLevel().equals(ip)?
                                ipAddress
                                :null
                );
        InterfaceInfo interfaceInfo =new InterfaceInfo(url,new Date(),ipAddress);

        try {
            //历史记录少于频率数，直接跳到interval判断逻辑
            if (historyInvoke.size() >= metaData.getUnitCount()) {
                //判断指定间隔内是否能调用
                //看倒数前那次调用的时间是否在unittime内
                InterfaceInfo lastUnitCountInvoke = historyInvoke.get(historyInvoke.size() - metaData.getUnitCount());
                long interval = interfaceInfo.getInvokeTime().getTime() - lastUnitCountInvoke.getInvokeTime().getTime();
                if (interval < metaData.getUnitTime()) {
                    handleUnitTimeNotPass(httpServletResponse);
                    return false;
                }
            } else if(historyInvoke.size()==0){
                return true;
            }

            //调用间隔判断
            InterfaceInfo lastInvoke = historyInvoke.get(historyInvoke.size() - 1);
            long interval = interfaceInfo.getInvokeTime().getTime() - lastInvoke.getInvokeTime().getTime();
            LOGGER.debug("调用间隔判断 interval={}",interval);
            if (interval < metaData.getInterval()) {
                handleIntervalNotPass(httpServletResponse);
                return false;
            }

            return true;
        }finally {
            //不管调用是否成功 都需要记录
            List<InterfaceInfo> temp =interfaceInvokeMap.get(url);
            temp.add(interfaceInfo);
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    //预留方法 设置 用户名使用
    public abstract String getUserName(HttpServletRequest httpServletRequest);

    public abstract void handleUnitTimeNotPass(HttpServletResponse httpServletResponse);

    public abstract void handleIntervalNotPass(HttpServletResponse httpServletResponse);

    private List<InterfaceInfo> getInterfaceInfos(String url,FrequencyControlLevel level,String value)
    {
        List<InterfaceInfo> result =new ArrayList<>();

        List<InterfaceInfo> temp =interfaceInvokeMap.get(url);
        if(temp==null)
        {
            interfaceInvokeMap.put(url,new ArrayList<>());
            return result;
        }
        if(temp.size()==0)
        {
            return result;
        }

        if(all.equals(level))
        {
            return temp;
        }

        for(InterfaceInfo info:temp)
        {
            switch (level)
            {
                case username:
                    if(value.equals(info.getUsername()))
                    {
                        result.add(info);
                    }
                    break;
                case ip:
                    if(value.equals(info.getIpAddress()))
                    {
                        result.add(info);
                    }
                    break;
            }
        }

        return result;
    }

    private static void readXmlConfig()
    {
        Document document =null;
        SAXReader saxReader =new SAXReader();

        ClassLoader classLoader =Thread.currentThread().getContextClassLoader();
        try {
            document=saxReader.read(classLoader.getResourceAsStream("frequency-metadata.xml"));
            Element config =document.getRootElement();

            for(Iterator<Element> elementIterator = config.elementIterator(); elementIterator.hasNext();)
            {
                Element element= elementIterator.next();
                try {
                    if(element.getName().equalsIgnoreCase("interface"))
                    {
                        String url =element.attributeValue("url");
                        long unitTime =Long.parseLong(element.attributeValue("unitTime"));
                        int unitCount =Integer.parseInt(element.attributeValue("unitCount"));
                        FrequencyControlLevel level =FrequencyControlLevel.valueOf(element.attributeValue("level"));
                        long interval =Long.parseLong(element.attributeValue("interval"));

                        FrequencyMetaData metaData = new FrequencyMetaData(url, unitTime, unitCount , level, interval);
                        metaDataMap.put(element.attributeValue("url"),metaData);
                        LOGGER.debug("加载FrequencyInterceptor元数据[url:{};unitTime:{};unitCount:{};level:{};interval:{}]",url, unitTime, unitCount , level, interval);

                        //初始化调用记录
                        interfaceInvokeMap.put(element.attributeValue("url"),new ArrayList<>());
                    }
                }catch (Exception ex)
                {
                    LOGGER.error("元数据解析错误 url:{}",element.attributeValue("url"));
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
            LOGGER.error("读取配置文件出错");
        }
    }
}
