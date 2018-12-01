package com.lijingya.util;

import java.io.Closeable;

/**
 * @author lijingya
 * @description 添加描述
 * @email lijingya@91118.com
 * @createDate 2018/11/29
 * @company 杭州天音
 */
public class IoUtil {

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
