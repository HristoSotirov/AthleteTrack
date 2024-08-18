package com.athletetrack.model;

public class Coach extends User {

    private String sport;
    public Coach() {
        super();
    }
    public Coach(Long id, String username, String password, String phoneNumber, String email, String sport) {
        super(id, username, password, phoneNumber, email);
        this.sport = sport;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}