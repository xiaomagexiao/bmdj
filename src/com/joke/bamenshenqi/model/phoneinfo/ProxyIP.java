package com.joke.bamenshenqi.model.phoneinfo;


public class ProxyIP {
    private String password;
    private int port;
    private String proxyIP;
    private String userName;

    public ProxyIP() {
        super();
    }

    public String getPassword() {
        return this.password;
    }

    public int getPort() {
        return this.port;
    }

    public String getProxyIP() {
        return this.proxyIP;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setProxyIP(String proxyIP) {
        this.proxyIP = proxyIP;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String toString() {
        return "ProxyIPEntity [proxyIP=" + this.proxyIP + ", userName=" + this.userName + ", password=" + this.password + ", port=" + this.port + "]";
    }
}

