package com.lijingya.util;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author lijingya
 * @description IO工具类
 * @email lijingya@91118.com
 * @createDate 2018/11/29
 * @company 杭州天音
 */
public class IoUtils {

    /**
     * 关闭Io流
     *
     * @param closeables Closeable
     */
    public static void closeIO(final Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
