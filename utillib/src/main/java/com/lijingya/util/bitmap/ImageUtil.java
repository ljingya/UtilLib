package com.lijingya.util.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import java.io.FileDescriptor;

/**
 * @author lijingya
 * @description 图片工具
 * @email lijingya@91118.com
 * @createDate 2018/11/27
 * @company 杭州天音
 */
public class ImageUtil {

    /**
     * 从resource中压缩图片
     *
     * @param res Resources
     * @param viewId id
     * @param reqWidth reqWidth
     * @param reqHeight reqHeight
     * @return Bitmap
     */
    public static Bitmap resizePictureFromResource(Resources res, int viewId, int reqWidth, int reqHeight) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, viewId, options);
        options.inSampleSize = calculateSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, viewId, options);
    }

    /**
     * 获取缩放比
     *
     * @param options Options
     * @param reqWidth int
     * @param reqHeight int
     * @return int
     */
    public static int calculateSampleSize(Options options, int reqWidth, int reqHeight) {
        if (reqWidth == 0 || reqHeight == 0) {
            return 1;
        }
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            int halfWidth = height / 2;
            int halfHeight = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * 从文件的描述符中获取bitmap
     *
     * @param fd 文件描述符
     * @param reqWidth 宽度
     * @param reqHeight 导读
     * @return Bitmap
     */
    public static Bitmap resizePictureFromFileDescriptor(FileDescriptor fd, int reqWidth, int reqHeight) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fd, null, options);
        options.inSampleSize = calculateSampleSize(options, reqWidth, reqWidth);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fd, null, options);
    }
}
