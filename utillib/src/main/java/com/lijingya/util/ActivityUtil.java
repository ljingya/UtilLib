package com.lijingya.util;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * @author Administrator
 * @description Activity相关的工具类
 * @email Administrator@91118.com
 * @createDate 2018/12/6
 * @company 杭州天音
 */
public class ActivityUtil {

    /**
     * 获取该Actviity的任务栈名称
     * @param activity
     * @return
     */
    public static String getActivityTaskAffinity(Activity activity){
        try {
            ActivityInfo activityInfo= activity.getPackageManager().getActivityInfo(activity.getComponentName(), PackageManager.GET_META_DATA);
       return activityInfo.taskAffinity;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

}
