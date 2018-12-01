package com.lijingya.util;

import android.content.Context;
import android.os.Environment;

/**
 * @author lijingya
 * @description 文件路径相关的类
 * @email lijingya@91118.com
 * @createDate 2018/11/27
 * @company 杭州天音
 */
public class PathUtils {

    private PathUtils() {
        throw new UnsupportedOperationException("不支持构造函数");
    }

    /**
     * 判断Sd是否挂载
     *
     * @return boolean
     */
    public static boolean isSdMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * @return /system
     */
    public static String getRootPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    /**
     * @return /data
     */
    public static String getDataPath() {
        return Environment.getDataDirectory().getAbsolutePath();
    }

    /**
     * @return /cache
     */
    public static String getDownloadCachePath() {
        return Environment.getDownloadCacheDirectory().getAbsolutePath();
    }





    /*    内部存储路径  */

    /**
     * 当安卓设备的存储空间少，或者不够用的时候，系统会自动删除这个目录下的文件，官方建议:超过1MB的文件。
     * 建议存储到getExternalCacheDir()目录下
     *
     * @return /data/data/packagename/cache
     */
    public static String getAppInternalCacheDir() {
        return AppUtil.getApp().getCacheDir().getAbsolutePath();
    }

    /**
     * @return String /data/data/packagename/files
     */
    public static String getAppInternalFilesDir() {
        return AppUtil.getApp().getFilesDir().getAbsolutePath();
    }

    /**
     * @return /data/data/packagename/databases
     */
    public static String getAppInternalDbsPath() {
        return AppUtil.getApp().getApplicationInfo().dataDir + "/databases";
    }

    /**
     * @param name 库的名称
     * @return /data/data/packagename/databases/name
     */
    public static String getAppInternalDbPath(String name) {
        return AppUtil.getApp().getDatabasePath(name).getAbsolutePath();
    }

    /**
     * @return /data/data/package/shared_prefs
     */
    public static String getAppInternalSpPath() {
        return AppUtil.getApp().getApplicationInfo().dataDir + "shared_prefs";
    }


    /*    外部存储路径  */

    /**
     * @return /storage/emulated/0
     */
    public static String getExtenralStoragePath() {
        if (!isSdMounted()) {
            return "";
        }
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }


    /**
     * 应用外部存储空间(数据文件私有，系统媒体文件无法访问）
     * 可以作为外部缓存的路径,卸载app时，会自动删除文件（前提是使用虚拟外部存储）
     *
     * @return /storage/emulated/0/Android/data/packagename/cache
     */
    public static String getAppExternalCachePath() {
        if (!isSdMounted()) {
            return "";
        }
        return AppUtil.getApp().getExternalCacheDir().getAbsolutePath();
    }

    /**
     * 应用外部存储空间(数据文件私有，系统媒体文件无法访问
     * 卸载app时，会自动删除文件（前提是使用虚拟外部存储）
     *
     * @param context
     * @return /storage/emulated/0/Android/data/packagename/files
     */
    public static String getAppExternalFilesPath(Context context) {
        if (!isSdMounted()) {
            return "";
        }
        return context.getExternalFilesDir(null).getAbsolutePath();
    }

    /**
     * @param type DIRECTORY_MUSIC, DIRECTORY_PODCASTS, DIRECTORY_RINGTONES,
     *             DIRECTORY_ALARMS, DIRECTORY_NOTIFICATIONS, DIRECTORY_PICTURES,
     *             DIRECTORY_MOVIES, DIRECTORY_DOWNLOADS, DIRECTORY_DCIM,
     *             DIRECTORY_DOCUMENTS
     * @return 例如当 DIRECTORY_DCIM 路径为 /storage/emulated/0/Android/data/packagename/files/DCIM
     */
    public static String getAppExternalFilesByTypePath(String type) {
        if (!isSdMounted()) {
            return "";
        }
        return AppUtil.getApp().getExternalFilesDir(type).getAbsolutePath();
    }

    /**
     * 应用外部存储空间（数据文件非私有，可以被手机的系统程序访问
     * 这个目录是用来存放各种类型的文件的目录，
     * 在这里用户可以分类管理不同类型的文件（例如音乐、图片、电影等）
     *
     * @param type DIRECTORY_MUSIC, DIRECTORY_PODCASTS, DIRECTORY_RINGTONES,
     *             DIRECTORY_ALARMS, DIRECTORY_NOTIFICATIONS, DIRECTORY_PICTURES,
     *             DIRECTORY_MOVIES, DIRECTORY_DOWNLOADS, DIRECTORY_DCIM,
     *             DIRECTORY_DOCUMENTS
     * @return 当 type 为 Environment.DIRECTORY_DCIM 时路径为 /storage/emulated/0/DCIM
     */
    public static String getExternalStoragePublicPath(String type) {
        if (!isSdMounted()) {
            return "";
        }
        return Environment.getExternalStoragePublicDirectory(type).getAbsolutePath();
    }
}
