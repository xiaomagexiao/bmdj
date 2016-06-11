package com.joke.bamenshenqi.util;


import android.os.Build;

public class SystemUtils {
    public static final int DEFAULT_THREAD_POOL_SIZE = 1;

    private SystemUtils() {
        super();
        throw new AssertionError();
    }

    public static int getDefaultThreadPoolSize() {
        return SystemUtils.getDefaultThreadPoolSize(8);
    }

    public static int getDefaultThreadPoolSize(int max) {
        int v0 = Runtime.getRuntime().availableProcessors() * 2 + 1;
        if(v0 <= max) {
            max = v0;
        }

        return max;
    }

    public static boolean hasHoneycomb() {
        boolean v0 = Build.VERSION.SDK_INT >= 11 ? true : false;
        return v0;
    }
}

