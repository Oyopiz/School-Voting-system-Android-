package com.oyopizmanga.vyingsys;

public class Userhelper {
    String username, regno, url, firstname, secondname, identity, position,uid;

    public Userhelper(String username, String regno, String url, String firstname, String secondname, String identity, String position, String uid) {
        this.username = username;
        this.regno = regno;
        this.url = url;
        this.firstname = firstname;
        this.secondname = secondname;
        this.identity = identity;
        this.position = position;
        this.uid = uid;
    }

    public Userhelper() {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
