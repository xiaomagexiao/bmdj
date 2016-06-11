package com.joke.bamenshenqi.db.greendao;


public class Student {
    private Long id;
    private String name;
    private String score;
    private String typeName;

    public Student() {
        super();
    }

    public Student(Long id) {
        super();
        this.id = id;
    }

    public Student(Long id, String name, String score, String typeName) {
        super();
        this.id = id;
        this.name = name;
        this.score = score;
        this.typeName = typeName;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getScore() {
        return this.score;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}

