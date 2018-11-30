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
     * @return
     */
    public static boolean isSdMounted(){
       return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 这个目录和getFilesDir()目录最大的不同在于：当安卓设备的存储空间少，
     * 或者不够用的时候，系统会自动删除这个目录下的文件，官方建议是，超过1MB的文件，
     * 建议存储到getExternalCacheDir()目录下
     * /data/data/packagename/cache
     * @param context
     * @return
     */
    public static String getCacheDir(Context context){
        return context.getCacheDir().getAbsolutePath();
    }

    /**
     * /data/data/packagename/files
     * @param context
     * @return
     */
    public static String getFilesDir(Context context){
        return context.getFilesDir().getAbsolutePath();
    }


}
