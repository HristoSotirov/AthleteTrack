package com.athletetrack.entity;

public class UserWorkoutEntity {

    private Long id;
    private Long userId;
    private String workoutTypes;

    public UserWorkoutEntity() {
    }

    public UserWorkoutEntity(Long id, Long userId, String workoutTypes) {
        this.id = id;
        this.userId = userId;
        this.workoutTypes = workoutTypes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getWorkoutTypes() {
        return workoutTypes;
    }

    public void setWorkoutTypes(String workoutTypes) {
        this.workoutTypes = workoutTypes;
    }

    @Override
    public String toString() {
        return "UserWorkoutEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", workoutTypes='" + workoutTypes + '\'' +
                '}';
    }
}
