package com.joke.bamenshenqi.util;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DpPxUtil {
    public DpPxUtil() {
        super();
    }

    public static int dip2px(Context context, float dpValue) {
        return ((int)(dpValue * context.getResources().getDisplayMetrics().density + 0.5f));
    }

    public static DisplayMetrics initWidthAndHeight(Context paramContext) {
        DisplayMetrics v0 = new DisplayMetrics();
        ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(v0);
        return v0;
    }

    public static int px2dip(Context context, float pxValue) {
        return ((int)(pxValue / context.getResources().getDisplayMetrics().density + 0.5f));
    }

    public static int px2sp(Context context, float pxValue) {
        return ((int)(pxValue / context.getResources().getDisplayMetrics().density + 0.5f));
    }

    public static int sp2px(Context context, float pxValue) {
        return ((int)(pxValue * context.getResources().getDisplayMetrics().density + 0.5f));
    }
}

