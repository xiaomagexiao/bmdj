package com.joke.bamenshenqi.model.phoneinfo;


public class Phonenet {
    private Integer netid;
    private String networkcountryiso;
    private String networkoperator;
    private String networkoperatorname;
    private String networktype;
    private String simcountryiso;
    private String simoperator;
    private String simoperatorname;
    private String simserialnumber;
    private String simstate;

    public Phonenet() {
        super();
    }

    public Integer getNetid() {
        return this.netid;
    }

    public String getNetworkcountryiso() {
        return this.networkcountryiso;
    }

    public String getNetworkoperator() {
        return this.networkoperator;
    }

    public String getNetworkoperatorname() {
        return this.networkoperatorname;
    }

    public String getNetworktype() {
        return this.networktype;
    }

    public String getSimcountryiso() {
        return this.simcountryiso;
    }

    public String getSimoperator() {
        return this.simoperator;
    }

    public String getSimoperatorname() {
        return this.simoperatorname;
    }

    public String getSimserialnumber() {
        return this.simserialnumber;
    }

    public String getSimstate() {
        return this.simstate;
    }

    public void setNetid(Integer netid) {
        this.netid = netid;
    }

    public void setNetworkcountryiso(String networkcountryiso) {
        String v0 = networkcountryiso == null ? null : networkcountryiso.trim();
        this.networkcountryiso = v0;
    }

    public void setNetworkoperator(String networkoperator) {
        String v0 = networkoperator == null ? null : networkoperator.trim();
        this.networkoperator = v0;
    }

    public void setNetworkoperatorname(String networkoperatorname) {
        String v0 = networkoperatorname == null ? null : networkoperatorname.trim();
        this.networkoperatorname = v0;
    }

    public void setNetworktype(String networktype) {
        String v0 = networktype == null ? null : networktype.trim();
        this.networktype = v0;
    }

    public void setSimcountryiso(String simcountryiso) {
        String v0 = simcountryiso == null ? null : simcountryiso.trim();
        this.simcountryiso = v0;
    }

    public void setSimoperator(String simoperator) {
        String v0 = simoperator == null ? null : simoperator.trim();
        this.simoperator = v0;
    }

    public void setSimoperatorname(String simoperatorname) {
        String v0 = simoperatorname == null ? null : simoperatorname.trim();
        this.simoperatorname = v0;
    }

    public void setSimserialnumber(String simserialnumber) {
        String v0 = simserialnumber == null ? null : simserialnumber.trim();
        this.simserialnumber = v0;
    }

    public void setSimstate(String simstate) {
        String v0 = simstate == null ? null : simstate.trim();
        this.simstate = v0;
    }
}

