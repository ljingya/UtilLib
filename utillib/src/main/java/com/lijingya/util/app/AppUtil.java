package com.lijingya.util.app;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
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
public class AppUtil {

    /**
     * 获取指定进程id的进程名称
     *
     * @param pid 进程id
     */
    public static String getProccessName(Context context, int pid) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> list = am.getRunningAppProcesses();
        Iterator<RunningAppProcessInfo> iterator = list.iterator();
        while (iterator.hasNext()) {
            RunningAppProcessInfo info = iterator.next();
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
