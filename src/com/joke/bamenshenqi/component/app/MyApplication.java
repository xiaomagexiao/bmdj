package com.joke.bamenshenqi.component.app;


import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private boolean mIsNetworkAvailable;
    private static MyApplication sInstance;

    public MyApplication() {
        super();
    }

    public static Context getContext() {
        return MyApplication.sInstance.getApplicationContext();
    }

    public static boolean isNetworkAvailable() {
        return MyApplication.sInstance.mIsNetworkAvailable;
    }

    public void onCreate() {
        super.onCreate();
        MyApplication.sInstance = this;
    }

    public void onTerminate() {
        super.onTerminate();
    }
}

