package com.athletetrack.model;

public class Admin extends User {

    public Admin() {
        super();
    }
    public Admin(Long id, String username, String password, String phoneNumber, String email) {
        super(id, username, password, phoneNumber, email);
    }
}
