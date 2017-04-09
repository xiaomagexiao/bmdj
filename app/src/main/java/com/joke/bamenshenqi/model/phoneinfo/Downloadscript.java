package com.joke.bamenshenqi.model.phoneinfo;


public class Downloadscript {
    private Integer apkid;
    private String scriptdownloadurl;
    private Integer scriptid;
    private String scriptname;
    private String scriptsavedpath;

    public Downloadscript() {
        super();
    }

    public Integer getApkid() {
        return this.apkid;
    }

    public String getScriptdownloadurl() {
        return this.scriptdownloadurl;
    }

    public Integer getScriptid() {
        return this.scriptid;
    }

    public String getScriptname() {
        return this.scriptname;
    }

    public String getScriptsavedpath() {
        return this.scriptsavedpath;
    }

    public void setApkid(Integer apkid) {
        this.apkid = apkid;
    }

    public void setScriptdownloadurl(String scriptdownloadurl) {
        String v0 = scriptdownloadurl == null ? null : scriptdownloadurl.trim();
        this.scriptdownloadurl = v0;
    }

    public void setScriptid(Integer scriptid) {
        this.scriptid = scriptid;
    }

    public void setScriptname(String scriptname) {
        String v0 = scriptname == null ? null : scriptname.trim();
        this.scriptname = v0;
    }

    public void setScriptsavedpath(String scriptsavedpath) {
        String v0 = scriptsavedpath == null ? null : scriptsavedpath.trim();
        this.scriptsavedpath = v0;
    }
}

