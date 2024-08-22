package com.athletetrack.DTO;

import java.time.Instant;

public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String userType;
    private Long coachId;
    private String coachName;
    private String sport;
    private String club;
    private Instant createdAt;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String username, String email, String phone, String userType, Long coachId, String coachName, String sport, String club, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
        this.coachId = coachId;
        this.coachName = coachName;
        this.sport = sport;
        this.club = club;
        this.createdAt = createdAt;
    }

    // COACH
    public UserDTO(String name, String username, String password, String email, String phone, String sport, String club) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.sport = sport;
        this.club = club;
    }

    // ATHLETE
    public UserDTO(String name, String username, String password, String email, String phone) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    // Login
    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", userType='" + userType + '\'' +
                ", coachId=" + coachId +
                ", coachName='" + coachName + '\'' +
                ", sport='" + sport + '\'' +
                ", club='" + club + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}