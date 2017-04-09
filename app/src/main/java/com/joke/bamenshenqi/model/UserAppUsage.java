package com.joke.bamenshenqi.model;


import java.io.Serializable;
import java.util.Date;

public class UserAppUsage implements Serializable {
    private String dataRecord;
    private String millisecondUse;
    private Date opRecord;
    private String packageName;
    private String uid;
    private String versionCode;

    public UserAppUsage() {
        super();
    }

    public UserAppUsage(String uid, String packageName, String versionCode, String dataRecord, String millisecondUse) {
        super();
        this.uid = uid;
        this.packageName = packageName;
        this.versionCode = versionCode;
        this.dataRecord = dataRecord;
        this.millisecondUse = millisecondUse;
    }

    public String getDataRecord() {
        return this.dataRecord;
    }

    public String getMillisecondUse() {
        return this.millisecondUse;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getUid() {
        return this.uid;
    }

    public String getVersionCode() {
        return this.versionCode;
    }

    public Date getopRecord() {
        return this.opRecord;
    }

    public void setDataRecord(String dataRecord) {
        this.dataRecord = dataRecord;
    }

    public void setMillisecondUse(String millisecondUse) {
        this.millisecondUse = millisecondUse;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public void setopRecord(Date opRecord) {
        this.opRecord = opRecord;
    }
}

