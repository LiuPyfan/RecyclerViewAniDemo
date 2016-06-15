package com.zdd.recyclerviewanidemo.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * @ClassName: RecyclerViewAniDemo
 * @CreateDate: 16/6/14 下午1:17
 * @Author: lucky
 * @Description:
 * @Version: [v1.0]
 */
public class ScreenUtil {
    private ScreenUtil() {
    }

    public static int getScreenHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    public static int getScreenWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay()
                .getMetrics(displayMetrics);
        return displayMetrics;
    }
}
