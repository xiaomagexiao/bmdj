package com.joke.bamenshenqi.model;


public class ResponseEntity {
    private String message;
    private String result;
    private int status;

    public ResponseEntity() {
        super();
    }

    public ResponseEntity(int status, String message, String result) {
        super();
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public String getMessage() {
        return this.message;
    }

    public String getResult() {
        return this.result;
    }

    public int getStatus() {
        return this.status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String toString() {
        return "ResponseEntity [status=" + this.status + ", message=" + this.message + ", result=" + this.result + "]";
    }
}

