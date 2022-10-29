package com.oyopizmanga.vyingsys;

public class Uservoters {
    String username, regno,identity,uid;

    public Uservoters(String username, String regno, String identity, String uid) {
        this.username = username;
        this.regno = regno;
        this.identity = identity;
        this.uid = uid;
    }

    public Uservoters() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
