package com.oyopizmanga.vyingsys;

public class Results {
    String Name,votes;

    public Results(String name, String votes) {
        Name = name;
        this.votes = votes;
    }

    public Results() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }
}
