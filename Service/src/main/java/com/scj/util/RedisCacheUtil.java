package com.scj.util;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by shengcj on 2016/12/29.
 */
@Service
public class RedisCacheUtil<T> {

    @Resource(name="jedisTemplate")
    public RedisTemplate<String, T> redisTemplate;

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     * @return 缓存的对象
     */
    public ValueOperations<String, T> setCacheObject(String key, T value) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value);
        return operation;
    }

    public T getCacheObject(String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    public ListOperations<String, T> setCacheList(String key, List<T> dataList) {
        ListOperations<String, T> operations = redisTemplate.opsForList();
        if (dataList != null) {
            for (T t : dataList) {
                operations.rightPush(key, t);
            }
        }
        return operations;
    }

    public List<T> getCacheList(String key) {
        List<T> dataList = new ArrayList<T>();
        ListOperations<String, T> operations = redisTemplate.opsForList();
        long size = operations.size(key);
        for (int i = 0; i < size; i++) {
            dataList.add(operations.leftPop(key));
        }
        return dataList;
    }

    public BoundSetOperations<String, T> setCacheSet(String key, Set<T> set) {
        BoundSetOperations<String, T> setOperations = redisTemplate.boundSetOps(key);
        for (T t : set) {
            setOperations.add(t);
        }
        return setOperations;
    }

    public Set<T> getCacheSet(String key) {
        Set<T> dataSet = new HashSet<T>();
        BoundSetOperations<String, T> setOperations = redisTemplate.boundSetOps(key);
        long size = setOperations.size();
        for (int i = 0; i < size; i++) {
            dataSet.add(setOperations.pop());
        }
        return dataSet;
    }

    public HashOperations<String, String, T> setCacheMap(String key, Map<String, T> map) {
        HashOperations<String, String, T> operations = redisTemplate.opsForHash();
        if (map != null) {
            for (Map.Entry<String, T> entry : map.entrySet()) {
                operations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return operations;
    }

    public Map<String,T> getCacheMap(String key){
        HashOperations<String, String, T> operations = redisTemplate.opsForHash();
        return operations.entries(key);
    }
}
