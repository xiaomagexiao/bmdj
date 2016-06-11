package com.joke.bamenshenqi.component.entity;


import java.io.Serializable;

public class Chiocelock implements Serializable {
    public int positionlock;
    public String typelock;

    public Chiocelock() {
        super();
    }

    public int getPositionlock() {
        return this.positionlock;
    }

    public String getTypelock() {
        return this.typelock;
    }

    public void setPositionlock(int positionlock) {
        this.positionlock = positionlock;
    }

    public void setTypelock(String typelock) {
        this.typelock = typelock;
    }

    public String toString() {
        return "Chiocelock{positionlock=" + this.positionlock + ", typelock=\'" + this.typelock + '\'' + '}';
    }
}

