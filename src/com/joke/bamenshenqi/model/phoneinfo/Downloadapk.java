package com.joke.bamenshenqi.model.phoneinfo;


public class Downloadapk {
    private String apkchannel;
    private String apkdownloadurl;
    private Integer apkid;
    private String apkname;
    private String apksavedpath;
    private Integer apkversioncode;
    private String apkversionname;
    private String packagename;

    public Downloadapk() {
        super();
    }

    public String getApkchannel() {
        return this.apkchannel;
    }

    public String getApkdownloadurl() {
        return this.apkdownloadurl;
    }

    public Integer getApkid() {
        return this.apkid;
    }

    public String getApkname() {
        return this.apkname;
    }

    public String getApksavedpath() {
        return this.apksavedpath;
    }

    public Integer getApkversioncode() {
        return this.apkversioncode;
    }

    public String getApkversionname() {
        return this.apkversionname;
    }

    public String getPackagename() {
        return this.packagename;
    }

    public void setApkchannel(String apkchannel) {
        String v0 = apkchannel == null ? null : apkchannel.trim();
        this.apkchannel = v0;
    }

    public void setApkdownloadurl(String apkdownloadurl) {
        String v0 = apkdownloadurl == null ? null : apkdownloadurl.trim();
        this.apkdownloadurl = v0;
    }

    public void setApkid(Integer apkid) {
        this.apkid = apkid;
    }

    public void setApkname(String apkname) {
        String v0 = apkname == null ? null : apkname.trim();
        this.apkname = v0;
    }

    public void setApksavedpath(String apksavedpath) {
        String v0 = apksavedpath == null ? null : apksavedpath.trim();
        this.apksavedpath = v0;
    }

    public void setApkversioncode(Integer apkversioncode) {
        this.apkversioncode = apkversioncode;
    }

    public void setApkversionname(String apkversionname) {
        String v0 = apkversionname == null ? null : apkversionname.trim();
        this.apkversionname = v0;
    }

    public void setPackagename(String packagename) {
        String v0 = packagename == null ? null : packagename.trim();
        this.packagename = v0;
    }
}

