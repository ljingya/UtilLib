package com.lijingya.util;

import android.app.ActivityManager;
import android.content.Context;

import java.util.Iterator;
import java.util.List;

/**
 * @author lijingya
 * @description 获取程序信息的工具
 * @email lijingya@91118.com
 * @createDate 2018/11/27
 * @company 杭州天音
 */
public final class AppProcessUtils {

    private AppProcessUtils() {
        throw new UnsupportedOperationException("不支持构造函数");
    }

    /**
     * 获取指定进程id的进程名称
     *
     * @param pid 进程id
     */
    public static String getProccessName(int pid) {
        ActivityManager am = (ActivityManager) AppUtils.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = am.getRunningAppProcesses();
        Iterator<ActivityManager.RunningAppProcessInfo> iterator = list.iterator();
        while (iterator.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = iterator.next();
            try {
                if (info.pid == pid) {
                    return info.processName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
