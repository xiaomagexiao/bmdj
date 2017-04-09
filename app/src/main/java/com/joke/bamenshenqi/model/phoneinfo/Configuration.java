package com.joke.bamenshenqi.model.phoneinfo;


public class Configuration {
    private String channel;
    private Integer dayremain;
    private int maxSleep;
    private int maxTime;
    private int minSleep;
    private int minTime;
    private Integer monthremain;
    private String packagename;
    private String runcount;
    private String runscriptlist;
    private String scriptRate;
    private int versionCode;
    private Integer weekremain;

    public Configuration() {
        super();
    }

    public String getChannel() {
        return this.channel;
    }

    public Integer getDayremain() {
        return this.dayremain;
    }

    public int getMaxSleep() {
        return this.maxSleep;
    }

    public int getMaxTime() {
        return this.maxTime;
    }

    public int getMinSleep() {
        return this.minSleep;
    }

    public int getMinTime() {
        return this.minTime;
    }

    public Integer getMonthremain() {
        return this.monthremain;
    }

    public String getPackagename() {
        return this.packagename;
    }

    public String getRuncount() {
        return this.runcount;
    }

    public String getRunscriptlist() {
        return this.runscriptlist;
    }

    public String getScriptRate() {
        return this.scriptRate;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public Integer getWeekremain() {
        return this.weekremain;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setDayremain(Integer dayremain) {
        this.dayremain = dayremain;
    }

    public void setMaxSleep(int maxSleep) {
        this.maxSleep = maxSleep;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public void setMinSleep(int minSleep) {
        this.minSleep = minSleep;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public void setMonthremain(Integer monthremain) {
        this.monthremain = monthremain;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public void setRuncount(String runcount) {
        this.runcount = runcount;
    }

    public void setRunscriptlist(String runscriptlist) {
        this.runscriptlist = runscriptlist;
    }

    public void setScriptRate(String scriptRate) {
        this.scriptRate = scriptRate;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public void setWeekremain(Integer weekremain) {
        this.weekremain = weekremain;
    }

    public String toString() {
        return super.toString() + "Configuration [runcount=" + this.runcount + ", runscriptlist=" + this.runscriptlist + ", dayremain=" + this.dayremain + ", weekremain=" + this.weekremain + ", monthremain=" + this.monthremain + ", scriptRate=" + this.scriptRate + ", minTime=" + this.minTime + ", maxTime=" + this.maxTime + ", minSleep=" + this.minSleep + ", maxSleep=" + this.maxSleep + "]";
    }
}

