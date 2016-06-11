package com.joke.bamenshenqi.component.entity;


import java.io.Serializable;

public class ResiveInfo implements Serializable {
    public int id;
    public boolean is;
    public boolean islock;
    public String name;

    public ResiveInfo() {
        super();
        this.islock = true;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean is() {
        return this.is;
    }

    public boolean islock() {
        return this.islock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIs(boolean is) {
        this.is = is;
    }

    public void setIslock(boolean islock) {
        this.islock = islock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "ResiveInfo{id=" + this.id + ", name=\'" + this.name + '\'' + ", is=" + this.is + ", islock=" + this.islock + '}';
    }
}

