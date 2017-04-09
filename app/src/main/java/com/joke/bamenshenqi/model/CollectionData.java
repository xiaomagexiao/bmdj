package com.joke.bamenshenqi.model;


import java.io.Serializable;
import java.util.List;

public class CollectionData implements Serializable {
    private List hackPackages;
    private SystemInfo systemInfo;
    private List userAppUsages;

    public CollectionData() {
        super();
    }

    public List getHackPackages() {
        return this.hackPackages;
    }

    public SystemInfo getSystemInfo() {
        return this.systemInfo;
    }

    public List getUserAppUsages() {
        return this.userAppUsages;
    }

    public void setHackPackages(List arg1) {
        this.hackPackages = arg1;
    }

    public void setSystemInfo(SystemInfo systemInfo) {
        this.systemInfo = systemInfo;
    }

    public void setUserAppUsages(List arg1) {
        this.userAppUsages = arg1;
    }

    public String toString() {
        return "CollectionData{systemInfo=" + this.systemInfo + ", userAppUsages=" + this.userAppUsages + ", hackPackages=" + this.hackPackages + '}';
    }
}

