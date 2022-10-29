package com.oyopizmanga.vyingsys;

public class Uservote {
    String url, name, votes, uid;

    public Uservote(String url, String name, String votes, String uid) {
        this.url = url;
        this.name = name;
        this.votes = votes;
        this.uid = uid;
    }

    public Uservote() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
