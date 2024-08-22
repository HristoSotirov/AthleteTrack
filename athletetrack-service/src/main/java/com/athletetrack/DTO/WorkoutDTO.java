package com.athletetrack.DTO;

import java.time.LocalDateTime;

public class WorkoutDTO {
    private Long id;
    private Long athleteId;
    private String workoutType;
    private String description;
    private LocalDateTime doneAt;

    public WorkoutDTO() {
    }

    public WorkoutDTO(Long id, Long athleteId, String workoutType, String description, LocalDateTime doneAt) {
        this.id = id;
        this.athleteId = athleteId;
        this.workoutType = workoutType;
        this.description = description;
        this.doneAt = doneAt;
    }

    // add workout
    public WorkoutDTO(Long athleteId, String workoutType, String description, LocalDateTime doneAt) {
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
        return "WorkoutDTO{" +
                "id=" + id +
                ", athleteId=" + athleteId +
                ", workoutType='" + workoutType + '\'' +
                ", description='" + description + '\'' +
                ", doneAt=" + doneAt +
                '}';
    }
}
