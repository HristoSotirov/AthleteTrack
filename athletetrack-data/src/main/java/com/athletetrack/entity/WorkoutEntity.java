package com.athletetrack.entity;

import java.time.Instant;

public class WorkoutEntity {

    private Long id;
    private Long athleteId;
    private String description;
    private Instant doneAt;
    private String workoutType;

    public WorkoutEntity() {
    }

    public WorkoutEntity(Long id, Long athleteId, String description, Instant doneAt, String workoutType) {
        this.id = id;
        this.athleteId = athleteId;
        this.description = description;
        this.doneAt = doneAt;
        this.workoutType = workoutType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(Long athleteId) {
        this.athleteId = athleteId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(Instant doneAt) {
        this.doneAt = doneAt;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    @Override
    public String toString() {
        return "WorkoutEntity{" +
                "id=" + id +
                ", athleteId=" + athleteId +
                ", description='" + description + '\'' +
                ", doneAt=" + doneAt +
                ", workoutType='" + workoutType + '\'' +
                '}';
    }
}
