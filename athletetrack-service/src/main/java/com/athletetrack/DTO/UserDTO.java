package com.athletetrack.DTO;

import java.time.Instant;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String userType;
    private Long coachId;
    private String sport;
    private String club;
    private Instant createdAt;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String email, String phone, String userType, Long coachId, String sport, String club, Instant createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
        this.coachId = coachId;
        this.sport = sport;
        this.club = club;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", userType='" + userType + '\'' +
                ", coachId=" + coachId +
                ", sport='" + sport + '\'' +
                ", club='" + club + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}