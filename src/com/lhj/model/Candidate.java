package com.lhj.model;

public class Candidate {
    private int id;
    private String name;
    private String photoUrl;
    private int votes;

    public Candidate() {
    }

    public Candidate(int id, String name, String photoUrl, int votes) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", votes=" + votes +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
