package com.lijingya.util;

import java.io.Closeable;

/**
 * @author lijingya
 * @description IO工具类
 * @email lijingya@91118.com
 * @createDate 2018/11/29
 * @company 杭州天音
 */
public class IoUtils {

    /**
     * io流的关闭工具
     */
    public static <T extends Closeable> void close(T t) {
        try {
            if (t != null) {
                t.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
