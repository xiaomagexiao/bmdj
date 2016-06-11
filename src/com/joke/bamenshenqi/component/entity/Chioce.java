package com.joke.bamenshenqi.component.entity;


import java.io.Serializable;

public class Chioce implements Serializable {
    public int position;
    public String type;

    public Chioce() {
        super();
    }

    public int getPosition() {
        return this.position;
    }

    public String getType() {
        return this.type;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "Chioce{position=" + this.position + ", type=\'" + this.type + '\'' + '}';
    }
}

