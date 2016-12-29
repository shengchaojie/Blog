package com.scj.service.test;

import com.scj.util.RedisCacheUtil;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * Created by shengcj on 2016/12/29.
 */
public class RedisTest extends TestBase {
    @Resource
    private RedisCacheUtil<String> redisCacheUtil;

    @Test
    public void testInsertString() throws UnsupportedEncodingException {
        /*Charset cs = Charset.forName("GBK");
        CharBuffer cb =CharBuffer.wrap("scj123");
        ByteBuffer bbf =cs.encode(cb);
        byte[] valueByte =new byte[bbf.limit()];
        System.arraycopy(bbf.array(),0,valueByte,0,bbf.limit());
*/
        redisCacheUtil.setCacheObject("scj3", "盛超杰");
    }

    @Test
    public void testGetString() {
        String name =redisCacheUtil.getCacheObject("zw");
        System.out.println(name);
    }
}
