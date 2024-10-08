package com.athletetrack.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class WorkoutEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long athleteId;
    private String workoutType;
    private String description;
    private LocalDateTime doneAt;

    public WorkoutEntity() {
    }

    public WorkoutEntity(Long id, Long athleteId, String workoutType, String description, LocalDateTime doneAt) {
        this.id = id;
        this.athleteId = athleteId;
        this.workoutType = workoutType;
        this.description = description;
        this.doneAt = doneAt;
    }

    // add workout
    public WorkoutEntity(Long athleteId, String workoutType, String description, LocalDateTime doneAt) {
        this.athleteId = athleteId;
        this.workoutType = workoutType;
        this.description = description;
        this.doneAt = doneAt;
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

    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(LocalDateTime doneAt) {
        this.doneAt = doneAt;
    }

    @Override
    public String toString() {
        return "WorkoutEntity{" +
                "id=" + id +
                ", athleteId=" + athleteId +
                ", workoutType='" + workoutType + '\'' +
                ", description='" + description + '\'' +
                ", doneAt=" + doneAt +
                '}';
    }
}