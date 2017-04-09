package com.joke.bamenshenqi.component.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GradeRevise implements Serializable {
    public List chioceList;
    public List chiocelock;
    public int id;

    public GradeRevise() {
        super();
        this.chioceList = new ArrayList();
        this.chiocelock = new ArrayList();
    }

    public List getChioceList() {
        return this.chioceList;
    }

    public List getChiocelock() {
        return this.chiocelock;
    }

    public int getId() {
        return this.id;
    }

    public void setChioceList(List arg1) {
        this.chioceList = arg1;
    }

    public void setChiocelock(List arg1) {
        this.chiocelock = arg1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "GradeRevise{chioceList=" + this.chioceList + ", id=" + this.id + ", chiocelock=" + this.chiocelock + '}';
    }
}

