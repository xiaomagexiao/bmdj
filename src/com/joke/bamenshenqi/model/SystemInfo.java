package com.joke.bamenshenqi.model;


import java.io.Serializable;
import java.util.Date;

public class SystemInfo implements Serializable {
    private String androidVersion;
    private String baseband;
    private String country;
    private String description;
    private String fingerprint;
    private String hostname;
    private String imei;
    private String lang;
    private String linux;
    private String mac;
    private String manufacturer;
    private String model;
    private Date opRecord;
    private String ram;
    private String sdk;
    private String serialno;
    private String simalpha;
    private String simnumeric;
    private String typename;
    private String uid;

    public SystemInfo() {
        super();
    }

    public SystemInfo(String uid, String mac, String model, String manufacturer, String ram, String androidVersion, String sdk, String baseband, String typename, String hostname, String country, String lang, String description, String fingerprint, String imei, String serialno, String simnumeric, String simalpha, String linux) {
        this(uid, mac, model, manufacturer, ram, androidVersion, sdk, baseband, typename, hostname, country, lang, description, fingerprint, imei, serialno, simnumeric, simalpha, linux, null);
    }

    public SystemInfo(String uid, String mac, String model, String manufacturer, String ram, String androidVersion, String sdk, String baseband, String typename, String hostname, String country, String lang, String description, String fingerprint, String imei, String serialno, String simnumeric, String simalpha, String linux, Date opRecord) {
        super();
        this.uid = uid;
        this.mac = mac;
        this.model = model;
        this.manufacturer = manufacturer;
        this.ram = ram;
        this.androidVersion = androidVersion;
        this.sdk = sdk;
        this.baseband = baseband;
        this.typename = typename;
        this.hostname = hostname;
        this.country = country;
        this.lang = lang;
        this.description = description;
        this.fingerprint = fingerprint;
        this.imei = imei;
        this.serialno = serialno;
        this.simnumeric = simnumeric;
        this.simalpha = simalpha;
        this.linux = linux;
        this.opRecord = opRecord;
    }

    public String getAndroidVersion() {
        return this.androidVersion;
    }

    public String getBaseband() {
        return this.baseband;
    }

    public String getCountry() {
        return this.country;
    }

    public String getDescription() {
        return this.description;
    }

    public String getFingerprint() {
        return this.fingerprint;
    }

    public String getHostname() {
        return this.hostname;
    }

    public String getImei() {
        return this.imei;
    }

    public String getLang() {
        return this.lang;
    }

    public String getLanguage() {
        return this.lang;
    }

    public String getLinux() {
        return this.linux;
    }

    public String getMac() {
        return this.mac;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getModel() {
        return this.model;
    }

    public String getRam() {
        return this.ram;
    }

    public String getSdk() {
        return this.sdk;
    }

    public String getSerialno() {
        return this.serialno;
    }

    public String getSimalpha() {
        return this.simalpha;
    }

    public String getSimnumeric() {
        return this.simnumeric;
    }

    public String getTypename() {
        return this.typename;
    }

    public String getUid() {
        return this.uid;
    }

    public Date getopRecord() {
        return this.opRecord;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public void setBaseband(String baseband) {
        this.baseband = baseband;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setLanguage(String language) {
        this.lang = language;
    }

    public void setLinux(String linux) {
        this.linux = linux;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setSdk(String sdk) {
        this.sdk = sdk;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public void setSimalpha(String simalpha) {
        this.simalpha = simalpha;
    }

    public void setSimnumeric(String simnumeric) {
        this.simnumeric = simnumeric;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setopRecord(Date opRecord) {
        this.opRecord = opRecord;
    }
}

