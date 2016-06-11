package com.joke.bamenshenqi.component.entity;


import java.io.Serializable;

public class ReportInfo implements Serializable {
    private long base;
    public boolean is;
    public boolean islock;
    private String module;
    private long offset;
    private String value;

    public ReportInfo() {
        super();
    }

    public long getAttr() {
        return this.base + this.offset;
    }

    public long getBase() {
        return this.base;
    }

    public String getModule() {
        return this.module;
    }

    public long getOffset() {
        return this.offset;
    }

    public String getValue() {
        return this.value;
    }

    public boolean is() {
        return this.is;
    }

    public boolean isSameAttr(long other) {
        boolean v0 = this.getAttr() == other ? true : false;
        return v0;
    }

    public boolean islock() {
        return this.islock;
    }

    public void setBase(long base) {
        this.base = base;
    }

    public void setIs(boolean is) {
        this.is = is;
    }

    public void setIslock(boolean islock) {
        this.islock = islock;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return "ReportInfo{base=" + this.base + ", offset=" + this.offset + ", value=\'" + this.value + '\'' + ", module=\'" + this.module + '\'' + ", is=" + this.is + ", islock=" + this.islock + '}';
    }
}

