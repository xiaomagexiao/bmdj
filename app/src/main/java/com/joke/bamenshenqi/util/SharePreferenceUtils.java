package com.joke.bamenshenqi.util;


import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUtils {
    public SharePreferenceUtils() {
        super();
    }

    public static void clearSharePreference(Context context, String prefereceName, String key) {
        SharedPreferences.Editor v0 = context.getSharedPreferences(prefereceName, 0).edit();
        v0.remove(key);
        v0.commit();
    }

    public static boolean getBooleanSharePreference(Context context, String prefereceName, String key) {
        return context.getSharedPreferences(prefereceName, 0).getBoolean(key, false);
    }

    public static int getIntSharePreference(Context context, String prefereceName, String key) {
        return context.getSharedPreferences(prefereceName, 0).getInt(key, 0);
    }

    public static long getLongSharePreference(Context context, String prefereceName, String key) {
        return context.getSharedPreferences(prefereceName, 0).getLong(key, 1);
    }

    public static boolean getMIUIState(Context context) {
        return context.getSharedPreferences("v5", 0).getBoolean("open", false);
    }

    public static boolean getProtocolState(Context context) {
        return context.getSharedPreferences("protocol", 0).getBoolean("first", false);
    }

    public static String getStringSharePreference(Context context, String prefereceName, String key) {
        return context.getSharedPreferences(prefereceName, 0).getString(key, "");
    }

    public static void setBooleanSharePreference(Context context, String prefereceName, String key, boolean value) {
        SharedPreferences.Editor v0 = context.getSharedPreferences(prefereceName, 0).edit();
        v0.putBoolean(key, value);
        v0.commit();
    }

    public static void setIntSharePreference(Context context, String prefereceName, String key, int value) {
        SharedPreferences.Editor v0 = context.getSharedPreferences(prefereceName, 0).edit();
        v0.putInt(key, value);
        v0.commit();
    }

    public static void setLongSharePreference(Context context, String prefereceName, String key, long value) {
        SharedPreferences.Editor v0 = context.getSharedPreferences(prefereceName, 0).edit();
        v0.putLong(key, value);
        v0.commit();
    }

    public static void setMIUIState(Context context, boolean flag) {
        SharedPreferences.Editor v0 = context.getSharedPreferences("v5", 0).edit();
        v0.putBoolean("open", flag);
        v0.commit();
    }

    public static void setProtocolState(Context context) {
        SharedPreferences.Editor v0 = context.getSharedPreferences("protocol", 0).edit();
        v0.putBoolean("first", true);
        v0.commit();
    }

    public static void setStringSharePreference(Context context, String prefereceName, String key, String value) {
        SharedPreferences.Editor v0 = context.getSharedPreferences(prefereceName, 0).edit();
        v0.putString(key, value);
        v0.commit();
    }
}

