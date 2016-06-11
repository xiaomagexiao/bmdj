package com.joke.bamenshenqi.model;


import java.io.Serializable;

public class ResponseForJNI implements Serializable {
    private String msg;
    private String result;
    private int status;

    public ResponseForJNI() {
        super();
    }

    public String getMsg() {
        return this.msg;
    }

    public String getResult() {
        return this.result;
    }

    public int getStatus() {
        return this.status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String toString() {
        return "ResponseForJNI{status=" + this.status + ", msg=\'" + this.msg + '\'' + ", result=\'" + this.result + '\'' + '}';
    }
}

