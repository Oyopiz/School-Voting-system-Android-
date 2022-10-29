package com.oyopizmanga.vyingsys;

public class Contest {
    String firstname, secondname, url,position;

    public Contest(String firstname, String secondname, String url, String position) {
        this.firstname = firstname;
        this.secondname = secondname;
        this.url = url;
        this.position = position;
    }

    public Contest() {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
