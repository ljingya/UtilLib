package com.lijingya.util.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

/**
 * Created by Omooo on 2018/12/1.
 */
public class ToastUtil {

    private static Toast mToast = null;

    public static void show(@NonNull Context context, String msg, int duration) {
        if (mToast != null) {
            mToast.setText(msg);
        } else {
            mToast = Toast.makeText(context, msg, duration);
            mToast.show();
        }
    }

    public static void show(Context context, int resId, int duration) {
        show(context, context.getString(resId), duration);
    }

    public static void show(Context context, String msg) {
        show(context, msg, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId) {
        show(context, context.getString(resId), Toast.LENGTH_SHORT);
    }
}
