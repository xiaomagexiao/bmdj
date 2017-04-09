package com.joke.bamenshenqi.util;


import android.util.Log;

public class LogUtil {
    private static final String TAG = "LogUtil";
    private static boolean isDebug;

    static {
        LogUtil.isDebug = true;
    }

    public LogUtil() {
        super();
    }

    public static void d(Object clazz, String msg) {
        if(LogUtil.isDebug) {
            Log.d(clazz.getClass().getSimpleName(), msg);
        }
    }

    public static void d(String msg) {
        if(LogUtil.isDebug) {
            Log.d("LogUtil", msg);
        }
    }

    public static void d(String tag, String msg) {
        if(LogUtil.isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void e(Object clazz, String msg) {
        if(LogUtil.isDebug) {
            Log.e(clazz.getClass().getSimpleName(), msg);
        }
    }

    public static void e(String tag, String msg) {
        if(LogUtil.isDebug) {
            Log.e(tag, msg);
        }
    }

    public static void e(String msg) {
        if(LogUtil.isDebug) {
            Log.e("LogUtil", msg);
        }
    }

    public static void i(Object clazz, String msg) {
        if(LogUtil.isDebug) {
            Log.i(clazz.getClass().getSimpleName(), msg);
        }
    }

    public static void i(String msg) {
        if(LogUtil.isDebug) {
            Log.i("LogUtil", msg);
        }
    }

    public static void i(String tag, String msg) {
        if(LogUtil.isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void setIsDebug(boolean debug) {
        LogUtil.isDebug = debug;
    }

    public static void v(Object clazz, String msg) {
        if(LogUtil.isDebug) {
            Log.v(clazz.getClass().getSimpleName(), msg);
        }
    }

    public static void v(String msg) {
        if(LogUtil.isDebug) {
            Log.v("LogUtil", msg);
        }
    }

    public static void v(String tag, String msg) {
        if(LogUtil.isDebug) {
            Log.v(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if(LogUtil.isDebug) {
            Log.w(tag, msg);
        }
    }

    public static void w(String msg) {
        if(LogUtil.isDebug) {
            Log.w("LogUtil", msg);
        }
    }
}

