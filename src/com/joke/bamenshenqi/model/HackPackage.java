package com.joke.bamenshenqi.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class HackPackage implements Serializable {
    private List hackDetail;
    private String hackPackageId;
    private Date opRecord;
    private String packageName;
    private String versionCode;

    public HackPackage() {
        super();
    }

    public HackPackage(String hackPackageId, String packageName, String versionCode, Date opRecord, List arg5) {
        super();
        this.hackPackageId = hackPackageId;
        this.packageName = packageName;
        this.versionCode = versionCode;
        this.opRecord = opRecord;
        this.hackDetail = arg5;
    }

    public HackPackage(String packageName, String versionCode, List arg9) {
        this(null, packageName, versionCode, null, arg9);
    }

    public List getHackDetail() {
        return this.hackDetail;
    }

    public String getHackPackageId() {
        return this.hackPackageId;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getVersionCode() {
        return this.versionCode;
    }

    public Date getopRecord() {
        return this.opRecord;
    }

    public void setHackDetail(List arg1) {
        this.hackDetail = arg1;
    }

    public void setHackPackageId(String hackPackageId) {
        this.hackPackageId = hackPackageId;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public void setopRecord(Date opRecord) {
        this.opRecord = opRecord;
    }

    public String toString() {
        return "HackPackage [hackPackageId=" + this.hackPackageId + ", packageName=" + this.packageName + ", versionCode=" + this.versionCode + ", opRecord=" + this.opRecord + ", hackDetail=" + this.hackDetail + "]";
    }
}

