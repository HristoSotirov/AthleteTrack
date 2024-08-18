package com.athletetrack.model;

public class Athlete extends User{

    private Coach coach;

    public Athlete() {
        super();
    }

    public Athlete(Long id, String username, String password, String phoneNumber, String email, Coach coach) {
        super(id, username, password, phoneNumber, email);
        this.coach = coach;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }
}
