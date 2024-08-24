package com.athletetrack.entity;

import java.util.List;
import java.util.Map;

public class AnalysisEntity {

    private int totalWorkouts;
    private Map<String, Integer> totalWorkoutsByType;
    private List<WorkoutEntity> workouts;

    public AnalysisEntity() {

    }

    public AnalysisEntity(int totalWorkouts, Map<String, Integer> totalWorkoutsByType, List<WorkoutEntity> workouts) {
        this.totalWorkouts = totalWorkouts;
        this.totalWorkoutsByType = totalWorkoutsByType;
        this.workouts = workouts;
    }

    public int getTotalWorkouts() {
        return totalWorkouts;
    }

    public void setTotalWorkouts(int totalWorkouts) {
        this.totalWorkouts = totalWorkouts;
    }

    public Map<String, Integer> getTotalWorkoutsByType() {
        return totalWorkoutsByType;
    }

    public void setTotalWorkoutsByType(Map<String, Integer> totalWorkoutsByType) {
        this.totalWorkoutsByType = totalWorkoutsByType;
    }

    public List<WorkoutEntity> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<WorkoutEntity> workouts) {
        this.workouts = workouts;
    }
}

