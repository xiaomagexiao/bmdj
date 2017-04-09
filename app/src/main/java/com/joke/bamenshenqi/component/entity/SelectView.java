package com.joke.bamenshenqi.component.entity;


import java.io.Serializable;

public class SelectView implements Serializable {
    public int id;
    public String typeview;

    public SelectView() {
        super();
    }

    public int getId() {
        return this.id;
    }

    public String getTypeview() {
        return this.typeview;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTypeview(String typeview) {
        this.typeview = typeview;
    }

    public String toString() {
        return "SelectView{id=" + this.id + ", typeview=\'" + this.typeview + '\'' + '}';
    }
}

