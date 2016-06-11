package com.joke.bamenshenqi.component.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReportListInfo implements Serializable {
    private List<ReportInfo> reDataList;

    public ReportListInfo() {
        super();
        this.reDataList = new ArrayList();
    }

    public List getReDataList() {
        return this.reDataList;
    }

    public void setReDataList(List arg1) {
        this.reDataList = arg1;
    }

    public String toString() {
        return "ReportListInfo{reDataList=" + this.reDataList + '}';
    }
}

