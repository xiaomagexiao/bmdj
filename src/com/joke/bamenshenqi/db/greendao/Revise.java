package com.joke.bamenshenqi.db.greendao;


public class Revise {
    private Long id;
    private String name;

    public Revise() {
        super();
    }

    public Revise(Long id) {
        super();
        this.id = id;
    }

    public Revise(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}

