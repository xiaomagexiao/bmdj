package com.joke.bamenshenqi.model;


import java.io.Serializable;
import java.util.Date;

public class HackPackageDetail implements Serializable {
    private String appBase;
    private String appMoudle;
    private String appOffset;
    private int hackCount;
    private String hackMeaning;
    private String hackPackageId;
    private String hackTarget;
    private Date oprecord;
    private int targetType;
    private String uid;

    public HackPackageDetail() {
        super();
    }

    public HackPackageDetail(String uid, String hackTarget, int targetType, String hackMeaning, String appMoudle, String appOffset, String appBase) {
        this(null, uid, hackTarget, targetType, hackMeaning, appMoudle, appOffset, appBase, 0, null);
    }

    public HackPackageDetail(String hackPackageId, String uid, String hackTarget, int targetType, String hackMeaning, String appMoudle, String appOffset, String appBase, int hackCount, Date oprecord) {
        super();
        this.hackPackageId = hackPackageId;
        this.uid = uid;
        this.hackTarget = hackTarget;
        this.targetType = targetType;
        this.hackMeaning = hackMeaning;
        this.appMoudle = appMoudle;
        this.appOffset = appOffset;
        this.appBase = appBase;
        this.hackCount = hackCount;
        this.oprecord = oprecord;
    }

    public String getAppBase() {
        return this.appBase;
    }

    public String getAppMoudle() {
        return this.appMoudle;
    }

    public String getAppOffset() {
        return this.appOffset;
    }

    public int getHackCount() {
        return this.hackCount;
    }

    public String getHackMeaning() {
        return this.hackMeaning;
    }

    public String getHackPackageId() {
        return this.hackPackageId;
    }

    public String getHackTarget() {
        return this.hackTarget;
    }

    public Date getOprecord() {
        return this.oprecord;
    }

    public int getTargetType() {
        return this.targetType;
    }

    public String getUid() {
        return this.uid;
    }

    public void setAppBase(String appBase) {
        this.appBase = appBase;
    }

    public void setAppMoudle(String appMoudle) {
        this.appMoudle = appMoudle;
    }

    public void setAppOffset(String appOffset) {
        this.appOffset = appOffset;
    }

    public void setHackCount(int hackCount) {
        this.hackCount = hackCount;
    }

    public void setHackMeaning(String hackMeaning) {
        this.hackMeaning = hackMeaning;
    }

    public void setHackPackageId(String hackPackageId) {
        this.hackPackageId = hackPackageId;
    }

    public void setHackTarget(String hackTarget) {
        this.hackTarget = hackTarget;
    }

    public void setOprecord(Date oprecord) {
        this.oprecord = oprecord;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String toString() {
        return "HackPackageDetail [hackPackageId=" + this.hackPackageId + ", uid=" + this.uid + ", hackTarget=" + this.hackTarget + ", targetType=" + this.targetType + ", hackMeaning=" + this.hackMeaning + ", appMoudle=" + this.appMoudle + ", appOffset=" + this.appOffset + ", appBase=" + this.appBase + ", hackCount=" + this.hackCount + ", oprecord=" + this.oprecord + "]";
    }
}

