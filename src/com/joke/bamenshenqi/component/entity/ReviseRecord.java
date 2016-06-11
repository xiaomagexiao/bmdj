package com.joke.bamenshenqi.component.entity;


import java.io.Serializable;

public class ReviseRecord implements Serializable {
    public int id;
    public boolean is;
    public boolean islock;
    public String name;

    public ReviseRecord() {
        super();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "ResiveInfo{id=" + this.id + ", name=\'" + this.name + '\'' + '}';
    }
}

