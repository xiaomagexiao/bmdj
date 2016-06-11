package com.joke.bamenshenqi.model;


import java.io.Serializable;

public class ProcessInfo implements Serializable {
    private String name;
    private int pid;
    private String uid;

    public ProcessInfo() {
        super();
    }

    public String getName() {
        return this.name;
    }

    public int getPid() {
        return this.pid;
    }

    public String getUid() {
        return this.uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String toString() {
        return "ProcessInfo{name=\'" + this.name + '\'' + ", pid=" + this.pid + ", uid=\'" + this.uid + '\'' + '}';
    }
}

