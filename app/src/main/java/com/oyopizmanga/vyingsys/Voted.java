package com.oyopizmanga.vyingsys;

public class Voted {
    String firstname, contestant,url, secondname,position;

    public Voted(String firstname, String contestant, String url, String secondname, String position) {
        this.firstname = firstname;
        this.contestant = contestant;
        this.url = url;
        this.secondname = secondname;
        this.position = position;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getContestant() {
        return contestant;
    }

    public void setContestant(String contestant) {
        this.contestant = contestant;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
