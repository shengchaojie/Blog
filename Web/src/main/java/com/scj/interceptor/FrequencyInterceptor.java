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

    //保存元数据
    private static final Map<String,FrequencyMetaData> metaDataMap =new HashMap<>();

    //加载元数据
    static
    {
        readXmlConfig();
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String url =httpServletRequest.getRequestURI();
        String ipAddress =httpServletRequest.getRemoteAddr();

        FrequencyMetaData metaData =metaDataMap.get("url");
        //如果元数据不存在，补监控
        if(metaData==null)
        {
            LOGGER.debug("{} 不存在配置的元数据",url);
            return true;
        }

        //拿到历史调用信息，如何做缓存？
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

        //历史记录为空，就全加入 huo 历史记录少于频率数，直接通过
        if(historyInvoke.size()==0||historyInvoke.size()<metaData.getUnitCount())
        {
            List<InterfaceInfo> temp =interfaceInvokeMap.get(url);
            temp.add(interfaceInfo);

            return true;
        }else {

        }

        //调用间隔判断
        InterfaceInfo lastInvoke =historyInvoke.get(historyInvoke.size()-1);
        long interval =interfaceInfo.getInvokeTime().getTime() -lastInvoke.getInvokeTime().getTime();
        if(interval<metaData.getInterval())
        {
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    //预留方法 设置 用户名使用
    public abstract String getUserName(HttpServletRequest httpServletRequest);

    private List<InterfaceInfo> getInterfaceInfos(String url,FrequencyControlLevel level,String value)
    {
        List<InterfaceInfo> result =new ArrayList<>();

        List<InterfaceInfo> temp =interfaceInvokeMap.get("url");
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
                        FrequencyMetaData metaData =
                                new FrequencyMetaData(
                                        element.attributeValue("url"),
                                        Long.parseLong(element.attributeValue("unitTime")) ,
                                        Integer.parseInt(element.attributeValue("unitCount")) ,
                                        FrequencyControlLevel.valueOf(element.attributeValue("level")),
                                        Long.parseLong(element.attributeValue("interval"))
                                        );
                        metaDataMap.put(element.attributeValue("url"),metaData);

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
