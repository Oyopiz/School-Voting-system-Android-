package com.oyopizmanga.vyingsys;

public class admin {
    String username, identity, regno;

    public admin(String username, String identity, String regno) {
        this.username = username;
        this.identity = identity;
        this.regno = regno;
    }

    public admin() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }
}
