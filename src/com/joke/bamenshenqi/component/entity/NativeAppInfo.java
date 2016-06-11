package com.joke.bamenshenqi.component.entity;


import android.graphics.drawable.Drawable;
import java.io.Serializable;

public class NativeAppInfo implements Serializable {
    private Drawable appIcon;
    private String appName;
    public boolean isSelected;
    private String name;
    private int pid;
    private String uid;

    public NativeAppInfo() {
        super();
    }

    public Drawable getAppIcon() {
        return this.appIcon;
    }

    public String getAppName() {
        return this.appName;
    }

    public String getName() {
        return this.name;
    }

    public int getPid() {
        return this.pid;
    }

    public String getUid() {
        return this.uid;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String toString() {
        return "NativeAppInfo{pid=" + this.pid + ", uid=\'" + this.uid + '\'' + ", name=\'" + this.name + '\'' + ", appIcon=" + this.appIcon + ", appName=\'" + this.appName + '\'' + ", isSelected=" + this.isSelected + '}';
    }
}

