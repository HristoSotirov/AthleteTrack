package com.athletetrack.entity;

import java.util.List;

public class WorkoutTypeEntity {

    private Long id;
    private Long userId;
    private List<String> workoutTypes;

    public WorkoutTypeEntity() {
    }

    public WorkoutTypeEntity(Long id, Long userId, List<String> workoutTypes) {
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

    public List<String> getWorkoutTypes() {
        return workoutTypes;
    }

    public void setWorkoutTypes(List<String> workoutTypes) {
        this.workoutTypes = workoutTypes;
    }

    @Override
    public String toString() {
        return "WorkoutTypeEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", workoutTypes=" + workoutTypes +
                '}';
    }
}