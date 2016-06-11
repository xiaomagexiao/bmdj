package com.joke.bamenshenqi.util;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

import com.joke.bamenshenqi.component.entity.NativeAppInfo;
import com.joke.bamenshenqi.model.CollectionData;

public class ActivityManager {
    private List<Activity> activityList;
    private CollectionData collectionData;
    private static ActivityManager instance;
    private NativeAppInfo nativeAppInfo;
    private String packageName;
    private String searchContent;

    private ActivityManager() {
        super();
        this.activityList = new LinkedList();
    }

    public void addActivity(Activity activity) {
        this.activityList.add(activity);
    }

    public void exit() {
        Iterator v1 = this.activityList.iterator();
        while(v1.hasNext()) {
            ((Activity) v1.next()).finish();
        }

        System.exit(0);
    }

    public void finish() {
        Iterator v1 = this.activityList.iterator();
        while(v1.hasNext()) {
            ((Activity) v1.next()).finish();
        }
    }

    public CollectionData getCollectionData() {
        return ActivityManager.instance.collectionData;
    }

    public static ActivityManager getInstance() {
        if(ActivityManager.instance == null) {
            ActivityManager.instance = new ActivityManager();
        }

        return ActivityManager.instance;
    }

    public NativeAppInfo getNativeAppInfo() {
        return ActivityManager.instance.nativeAppInfo;
    }

    public String getPackageName() {
        return ActivityManager.instance.packageName;
    }

    public String getSearchContent() {
        return ActivityManager.instance.searchContent;
    }

    public boolean isWifiConnected(Context context) {
        boolean v2 = true;
        if(!((ConnectivityManager)context.getSystemService("connectivity")).getNetworkInfo(1).isConnected()) {
            v2 = false;
        }

        return v2;
    }

    public void setCollectionData(CollectionData collectionData) {
        ActivityManager.instance.collectionData = collectionData;
    }

    public void setNativeAppInfo(NativeAppInfo nativeAppInfo) {
        ActivityManager.instance.nativeAppInfo = nativeAppInfo;
    }

    public void setPackageName(String packageName) {
        ActivityManager.instance.packageName = packageName;
    }

    public void setSearchContent(String searchContent) {
        ActivityManager.instance.searchContent = searchContent;
    }
}

