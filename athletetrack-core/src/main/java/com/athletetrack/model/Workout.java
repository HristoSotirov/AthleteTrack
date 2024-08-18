package com.athletetrack.model;

import java.time.LocalDateTime;

public class Workout {
    private Long id;
    private WorkoutType workoutType;
    private LocalDateTime dateTime;


    public Workout() {
    }

    public Workout(Long id, WorkoutType workoutType, LocalDateTime dateTime) {
        this.id = id;
        this.workoutType = workoutType;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkoutType getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(WorkoutType workoutType) {
        this.workoutType = workoutType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}

