package com.joke.bamenshenqi.model.phoneinfo;


public class Phoneadditional {
    private Integer additionalid;
    private String androidid;
    private String bassid;
    private String deviceid;
    private String macaddress;
    private String subscriberid;

    public Phoneadditional() {
        super();
    }

    public Integer getAdditionalid() {
        return this.additionalid;
    }

    public String getAndroidid() {
        return this.androidid;
    }

    public String getBassid() {
        return this.bassid;
    }

    public String getDeviceid() {
        return this.deviceid;
    }

    public String getMacaddress() {
        return this.macaddress;
    }

    public String getSubscriberid() {
        return this.subscriberid;
    }

    public void setAdditionalid(Integer additionalid) {
        this.additionalid = additionalid;
    }

    public void setAndroidid(String androidid) {
        String v0 = androidid == null ? null : androidid.trim();
        this.androidid = v0;
    }

    public void setBassid(String bassid) {
        String v0 = bassid == null ? null : bassid.trim();
        this.bassid = v0;
    }

    public void setDeviceid(String deviceid) {
        String v0 = deviceid == null ? null : deviceid.trim();
        this.deviceid = v0;
    }

    public void setMacaddress(String macaddress) {
        String v0 = macaddress == null ? null : macaddress.trim();
        this.macaddress = v0;
    }

    public void setSubscriberid(String subscriberid) {
        String v0 = subscriberid == null ? null : subscriberid.trim();
        this.subscriberid = v0;
    }
}

