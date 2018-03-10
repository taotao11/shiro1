package com.shiro.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * shiro md5 加密
 *
 */
public class ShiroMd5 {
    //md5 加密工具类
    public static String getMd5(String source,String alt){
        //使用shiro的md5 加密
        Object hash = new SimpleHash("md5",source, ByteSource.Util.bytes(alt),1);
        return hash.toString();
    }
}
