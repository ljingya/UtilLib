package com.lijingya.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;

import java.util.Iterator;
import java.util.List;

/**
 * @author lijingya
 * @description Application initialization
 * @email lijingya@91118.com
 * @createDate 2018/11/27
 * @company 杭州天音
 */
public final class AppUtil {

    private static Application sApplication;

    private AppUtil() {
        throw new UnsupportedOperationException("不支持初始化");
    }


    public static void init(Application app) {
        sApplication = app;
    }


    public static Application getApp() {
        return sApplication;
    }
}
