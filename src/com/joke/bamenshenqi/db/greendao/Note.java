package com.joke.bamenshenqi.db.greendao;


import java.util.Date;

public class Note {
    private String comment;
    private Date date;
    private Long id;
    private String text;

    public Note() {
        super();
    }

    public Note(Long id) {
        super();
        this.id = id;
    }

    public Note(Long id, String text, String comment, Date date) {
        super();
        this.id = id;
        this.text = text;
        this.comment = comment;
        this.date = date;
    }

    public String getComment() {
        return this.comment;
    }

    public Date getDate() {
        return this.date;
    }

    public Long getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }
}

