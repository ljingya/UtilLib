package com.lijingya.util.file;

import android.content.Context;
import android.os.Environment;

/**
 * @author lijingya
 * @description 文件工具
 * @email lijingya@91118.com
 * @createDate 2018/11/27
 * @company 杭州天音
 */
public class FileUtil {

    private static final String TAG = "FileUtil";

    /**
     * 判断Sd是否挂载
     *
     * @return boolean
     */
    public static boolean isSdMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }


    /*    内部存储路径  */

    /**
     * 这个目录和getFilesDir()目录最大的不同在于：当安卓设备的存储空间少，
     * 或者不够用的时候，系统会自动删除这个目录下的文件，官方建议是，超过1MB的文件，
     * 建议存储到getExternalCacheDir()目录下
     *
     * @param context 上下文环境
     * @return /data/data/packagename/cache
     */
    public static String getCacheDir(Context context) {
        return context.getCacheDir().getAbsolutePath();
    }

    /**
     * @param context 上下文环境
     * @return String /data/data/packagename/files
     */
    public static String getFilesDir(Context context) {
        return context.getFilesDir().getAbsolutePath();
    }



    /*    外部存储路径  */

    /**
     * 应用外部存储空间(数据文件私有，系统媒体文件无法访问）
     * 可以作为外部缓存的路径,卸载app时，会自动删除文件（前提是使用虚拟外部存储）
     *
     * @return /storage/emulated/0/Android/data/packagename/cache
     */
    public static String getExternalStorageDirectory(Context context) {
        return context.getExternalCacheDir().getAbsolutePath();
    }

    /**
     * 应用外部存储空间（数据文件非私有，可以被手机的系统程序访问）
     * 官方建议，不要直接使用该目录，为了避免污染用户的根命名空间，应用私有的数据，
     * 应该放在 Context.getExternalFilesDir目录下
     * 其他的可以被分享的文件，可以放在getExternalStoragePublicDirectory(String)目录下
     *
     * @return /storage/emulated/0
     */
    public static String getExternalStoragePublicDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }


}
